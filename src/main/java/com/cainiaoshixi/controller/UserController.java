package com.cainiaoshixi.controller;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.entity.User;
import com.cainiaoshixi.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

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
    private static final String APP_ID = "wx2d578cdea490f378";
    private static final String APP_SECRET = "d942d6b119614c4d71d0247713ce9707";
    private static final String TECENT_SERVER_URL = "https://api.weixin.qq.com/sns/jscode2session?";
    private static final String AVATAR_PATH = "/images/avatar/";

    private static final long expireTime = 86400; //sessionId有效时间，以秒为单位
    private static final int UPLOAD_AVATAR_FAIL_CODE = -101;  //保存头像失败错误码
    private static final Integer SAVE_USERBASEINFO_FAIL_CODE = -102; //时间解析错误

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
            String avatar = saveAvatar(request);
            user.setAvatarUrl(avatar);
            int userId = (int) request.getSession().getAttribute("userId");
            user.setId(userId);
            user.setName(request.getParameter("name"));
            user.setSex(Byte.parseByte(request.getParameter("sex")));
            user.setIdentity(Byte.parseByte(request.getParameter("identity")));
            user.setBirthday(dateFormat.parse(request.getParameter("birthday")));
            user.setResidence(request.getParameter("residence"));
            user.setEmail(request.getParameter("email"));
            user.setCellphone(request.getParameter("cellphone"));
            userService.updateUserById(user);
            return ResultUtil.success("");
        } catch (IOException e) {
            return ResultUtil.error(UPLOAD_AVATAR_FAIL_CODE, "上传头像失败");
        } catch (ParseException e) {
            return ResultUtil.error(SAVE_USERBASEINFO_FAIL_CODE, "时间解析错误");
        }
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 上传头像
     * @Date: 22:58 2018/6/6
     */
    private String saveAvatar(HttpServletRequest request) throws IOException {
        logger.info("saveAvatar start...");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("avatarUrl");
        String filePath = null;
        for (MultipartFile mf : fileList) {
            if(!mf.isEmpty()){
                String originFileName = mf.getOriginalFilename();
                String suffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
                String dirRealPath = request.getSession().getServletContext().getRealPath(AVATAR_PATH);
                File dir = new File(dirRealPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                filePath = dirRealPath + File.separator + UUID.randomUUID().toString().replace("-", "") + "." + suffix;
                logger.info("filePath: {}", filePath);
                mf.transferTo(new File(filePath));
            }
        }
        return filePath;
    }

    /**
     * @Author: Chy
     * @Param:
     * @Description: 获取用户基本信息
     * @Date: 18:57 2018/5/1
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo(HttpServletRequest request){
        int userId = (int) request.getSession().getAttribute("userId");
        User user = userService.getUserByPrimaryKey(userId);
        return ResultUtil.success(user);
    }

}