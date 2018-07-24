package com.cainiaoshixi.util;

import com.cainiaoshixi.domain.Result;

public class ResultUtil {

    public static<T> Result<T> success(T data) {
        return success(data, "success");
    }

    public static<T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static<T> Result<T> error(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}