package com.cainiaoshixi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;

@SessionScope
@Component
public class SessionUtil {

    private final HttpServletRequest request;

    @Autowired
    public SessionUtil(HttpServletRequest request) {
        this.request = request;
    }

    public Integer userId() {
        return (Integer) request.getSession().getAttribute("userId");
    }
}
