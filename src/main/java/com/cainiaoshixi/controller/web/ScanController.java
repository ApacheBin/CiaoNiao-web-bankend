package com.cainiaoshixi.controller.web;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Chy
 * @Description: C端扫码登录
 * @Date: Created at 23:03 2018/6/24
 */

@CrossOrigin
@Controller
@RequestMapping("/scan")
public class ScanController {
    @Autowired
    private RedisUtil redisUtil;
    private static final long EXPIRE_TIME = 86400; //sessionId有效时间，以秒为单位
    private static final String LOG_TITLE = "WEB_LoginController";
    private static final Logger logger = LoggerFactory.getLogger(ScanController.class);

    @RequestMapping("/login")
    @ResponseBody
    public Result scanToLogin(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        String token = request.getParameter("token");
        logger.info(LOG_TITLE + "scanToLogin, userId: {}, token: {}", userId, token);
        if (token == null || token.isEmpty())
            return ResultUtil.error(-201, "token为空");
        redisUtil.set(token, userId + "", EXPIRE_TIME);
        return ResultUtil.success("登录成功");
    }

    @RequestMapping("/checkStatus")
    @ResponseBody
    public Result checkLoginStatus(HttpServletRequest request) {
        String sessionId = (String) request.getSession().getAttribute("sessionId");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        logger.info(LOG_TITLE + "checkLoginStatus, userId: {}, token: {}", userId, sessionId);
        if (userId == null) {
            return ResultUtil.error(-301, "unLogin");
        }else {
            redisUtil.expire(sessionId, EXPIRE_TIME);
            Result<String> result = ResultUtil.success("login success");
            result.setData(Integer.toString(userId));
            return result;
        }
    }
}