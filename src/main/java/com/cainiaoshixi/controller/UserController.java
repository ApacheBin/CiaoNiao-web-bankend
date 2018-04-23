package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.CnUser;
import com.cainiaoshixi.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: Chy
 * @Description: 获取用户openId、解密用户信息等API
 * @Date: Created at 14:06 2018/4/9
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final String APP_ID = "wx41311a0239485c53";
    private static final String APP_SECRET = "442e8bcb9fc93f4b64c8578a6a1d3077";
    private static final String BASE_URL = "https://api.weixin.qq.com/sns/jscode2session?";
    private static final long expireTime = 86400; //sessionId有效时间，以秒为单位

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;
    /**
     * @Author: Chy
     * @Param:
     * @Description: 根据session_key创建第三方session维护登录态
     * @Date: 14:12 2018/4/9
     */
    @ResponseBody
    @RequestMapping("/getSessionId")
    public Result getSessionId(String code) throws Exception {

        //根据code获取用户的session_key和userId
        String userInfo = getUserInfoByCode(code);
        JSONObject jsonObject = JSON.parseObject(userInfo);
        String sessionKey = (String) jsonObject.getString("session_key");
        String openId = (String) jsonObject.getString("openid");

        //创建用户，并返回用户的主键
        int id = createUser(openId);

        //创建用户的sessionId，并将对应的用户id写入到redis
        String sessionId = UUID.randomUUID().toString().replace("-", "");
        redisUtil.set(sessionId, id + "", expireTime);

        //以json字符串形式返回sessionId
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("sessionId", sessionId);
        String resultData = JSON.toJSONString(resultMap);
        return ResultUtil.success(resultData);
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 根据客户端传过来的js_code去微信服务器获取session_key和openId
     * @Date: 18:46 2018/4/9
     */
    private String getUserInfoByCode(String code) throws IOException {

        HttpClient httpClient = HttpClients.createDefault();
        String param = "appid="+ APP_ID +"&secret=" + APP_SECRET + "&js_code="
                + code + "&grant_type=authorization_code";
        String url = BASE_URL + param;

        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpGet);
        String content = EntityUtils.toString(response.getEntity(), "UTF-8");   // 获取服务端返回的数据
        response.close();  //释放资源

        return content;
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 创建用户并保存到数据库
     * @Date: 18:48 2018/4/9
     */
    @ResponseBody
    @RequestMapping("/createUser")
    private int createUser(String openId) throws Exception {
        if (openId == null){
            throw new Exception("openId为空，创建用户失败！");
        }
        return userService.createUser(openId);
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 保存用户基本信息
     * @Date: 21:58 2018/4/9
     */
    @ResponseBody
    @RequestMapping("/saveUserInfo")
    public Result saveUserInfo(CnUser cnUser, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        String sessionId = cookies[0].getValue();
        int id = Integer.parseInt((String) redisUtil.get(sessionId));

        cnUser.setId(id);
        userService.updateUserById(cnUser);
        return ResultUtil.success("");
    }

}