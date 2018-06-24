package com.cainiaoshixi.controller.web;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Chy
 * @Description: C端扫码登录
 * @Date: Created at 23:03 2018/6/24
 */

@CrossOrigin
@Controller
public class LoginController {
    @Autowired
    private RedisUtil redisUtil;

    private static final long expireTime = 86400; //sessionId有效时间，以秒为单位

    @RequestMapping("/scanToLogin")
    public Result scanToLogin(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");
        String token = request.getParameter("token");
        if (token == null || token.isEmpty())
            return ResultUtil.error(-201, "token为空");
        redisUtil.set(token, userId + "", expireTime);
        return ResultUtil.success("登录成功");
    }
}