package com.cainiaoshixi.exception;

import com.cainiaoshixi.domain.Result;
import com.cainiaoshixi.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class CnExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(CnExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        logger.error("【系统异常】:", e);
        return ResultUtil.error(-1, "未知错误");
    }
}
