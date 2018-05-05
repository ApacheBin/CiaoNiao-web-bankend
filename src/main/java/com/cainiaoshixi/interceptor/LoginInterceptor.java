package com.cainiaoshixi.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.util.RedisUtil;
import com.cainiaoshixi.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return loginValidate(request, response);
    }

    private boolean loginValidate(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getParameter("sessionId");
        if (sessionId == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionId = cookie.getValue();
                        break;
                    }
                }
            }
        }
        if (sessionId != null && redisUtil.hasKey(sessionId)) {
            Integer userId = Integer.parseInt((String) redisUtil.get(sessionId));
            request.getSession().setAttribute("userId", userId);
            return true;
        } else {
            // 暂时处理方案，用于测试
            if (sessionId != null && sessionId.equals("testcainiaoshixi")) {
                request.getSession().setAttribute("userId", 1);
                logger.debug("Test Start");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        Result result = ResultUtil.error(-2, "没有登录权限");
        String json = JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
        try (PrintWriter out = response.getWriter()) {
            out.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
