package com.cainiaoshixi.controller;

import com.cainiaoshixi.entity.CnUser;
import com.cainiaoshixi.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @Autowired
    private IUserService userService;
    /**
     * @Author: Chy
     * @Param:
     * @Description: 根据session_key创建第三方session维护登录态
     * @Date: 14:12 2018/4/9
     */
    @ResponseBody
    @RequestMapping("/getSessionId")
    public String getSessionId(String code, HttpSession session) throws Exception {

        String userInfo = getUserInfoByCode(code);
        JSONObject jsonObject = JSON.parseObject(userInfo);
        String sessionKey = (String) jsonObject.getString("session_key");
        String openId = (String) jsonObject.getString("openid");

        String sessionId = UUID.randomUUID().toString().replace("-", "");
        session.setAttribute(sessionId, sessionKey + " " + openId);

        //创建用户并保存到数据库
        createUser(openId);

        return sessionId;
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
    public void createUser(String openId) throws Exception {
        if (openId == null)
            throw new Exception("openId为空");
        userService.createUser(openId);
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 保存用户基本信息
     * @Date: 21:58 2018/4/9
     */
    @ResponseBody
    @RequestMapping("/saveUserInfo")
    public String saveUserInfo(CnUser cnUser, HttpSession session, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        String sessionId = cookies[0].getValue();
        String openId = getOpenIdBySessionId(sessionId, session);

//        userService.updateUserInfoByOpenId(openID);
        return "success";
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 从session中获取用户的openId
     * @Date: 18:53 2018/4/9
     */
    private String getOpenIdBySessionId(String sessionId, HttpSession session) {
        String userInfo =(String)session.getAttribute(sessionId);
        if (userInfo == null){
            return null;
        }
        return userInfo.split(" ")[1];
    }
}