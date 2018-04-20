package cn.xiaobaiqiuzhi.admin.util;

import cn.xiaobaiqiuzhi.admin.domain.Result;

public class ResultUtil {

    public static<T>  Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMsg("success");
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