package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.User;
import com.cainiaoshixi.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.util.FileUtil;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import com.cainiaoshixi.util.SessionUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Chy
 * @Description: 获取用户openId、解密用户信息等API
 * @Date: Created at 14:06 2018/4/9
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final String APP_ID = "wxa0895de6bfdb7497";
    private static final String APP_SECRET = "3a0a1c252c369015fa1ea74baf85ad50";
    private static final String TECENT_SERVER_URL = "https://api.weixin.qq.com/sns/jscode2session?";
    private static final String AVATAR_DIR_PATH = "/data/images/avatar/";
    private static final String AVATAR_ROOT_URL = "cainiaoshixi.com/images/avatar/";

    private static final long expireTime = 86400; //sessionId有效时间，以秒为单位
    private static final int UPLOAD_AVATAR_FAIL_CODE = -101;  //保存头像失败错误码
    private static final Integer SAVE_USERBASEINFO_FAIL_CODE = -102; //时间解析错误
    private static final Integer GET_FILE_FAIL_CODE = -103; //获取multiparFile失败

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    private final SessionUtil session;

    @Autowired
    public UserController(SessionUtil session) {
        this.session = session;
    }

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

        //根据openId创建用户并返回
        User user = createUser(openId);

        //创建用户的sessionId，并将对应的用户id写入到redis
        String sessionId = UUID.randomUUID().toString().replace("-", "");
        redisUtil.set(sessionId, user.getId() + "", expireTime);

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
        String url = TECENT_SERVER_URL + param;

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
    private User createUser(String openId) throws Exception {
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
    public Result saveUserInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new User();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> fileList = multipartRequest.getFiles("avatarUrl");
            if (fileList != null && !fileList.isEmpty()){
                String fileName = FileUtil.uploadFile(fileList.get(0), AVATAR_DIR_PATH);
                user.setAvatarUrl("http://www.cainiaoshixi.com/data/images/avatar/" + fileName);  //配置了tomcat虚拟目录
            }else {
                return ResultUtil.error(GET_FILE_FAIL_CODE, "获取文件失败");
            }
            int userId = (int) request.getSession().getAttribute("userId");
            user.setId(userId);
            user.setName(request.getParameter("name"));
            user.setSex(Byte.parseByte(request.getParameter("sex")));
            user.setIdentity(Byte.parseByte(request.getParameter("identity")));
            user.setBirthday(dateFormat.parse(request.getParameter("birthday")));
            user.setResidence(request.getParameter("residence"));
            user.setEmail(request.getParameter("email"));
            user.setCellphone(request.getParameter("cellphone"));
            user.setSchool(request.getParameter("school"));
            user.setMajor(request.getParameter("major"));
            userService.updateUserById(user);
            return ResultUtil.success("保存成功");
        } catch (IOException e) {
            return ResultUtil.error(UPLOAD_AVATAR_FAIL_CODE, "上传头像失败");
        } catch (ParseException e) {
            return ResultUtil.error(SAVE_USERBASEINFO_FAIL_CODE, "时间解析异常");
        }
    }

    @PostMapping("/info/save")
    @ApiOperation("保存用户信息")
    @ResponseBody
    public Result saveUser(
            @RequestParam("name") String name,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "sex", required = false) Byte sex,
            @RequestParam(value = "birthday",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday,
            @RequestParam(value = "identity",required = false) Byte identity,
            @RequestParam(value = "residence", required = false) String residence,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "cellphone", required = false) String cellphone,
            @RequestParam(value = "school", required = false) String school,
            @RequestParam(value = "major", required = false) String major ) throws IOException {
        User user = new User();
        if(avatar != null) {
            String fileName = FileUtil.uploadFile(avatar, AVATAR_DIR_PATH);
            user.setAvatarUrl(fileName);
        }
        user.setName(name);
        user.setId(session.userId());
        user.setSex(sex);
        user.setBirthday(birthday);
        user.setIdentity(identity);
        user.setResidence(residence);
        user.setEmail(email);
        user.setCellphone(cellphone);
        user.setSchool(school);
        user.setMajor(major);
        userService.updateUserById(user);
        return ResultUtil.success("", "用户信息保存成功！");
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 获取用户基本信息
     * @Date: 18:57 2018/5/1
     */
    @RequestMapping("/info/get")
    @ResponseBody
    public Result getUserInfo(HttpServletRequest request){
        int userId = (int) request.getSession().getAttribute("userId");
        User user = userService.getUserByPrimaryKey(userId);
        if(user.getAvatarUrl() != null && !user.getAvatarUrl().startsWith("/") )
            user.setAvatarUrl(AVATAR_ROOT_URL + user.getAvatarUrl());
        user.setOpenId(null);
        return ResultUtil.success(user);
    }

}