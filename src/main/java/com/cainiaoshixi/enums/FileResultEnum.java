package com.cainiaoshixi.enums;

public enum FileResultEnum {

    FILE_FORMAT_NOT_SUPPORT(601, "不支持该文件格式！"),
    FILE_EXIST(602, "文件已存在！"),
    FILE_IS_EMPTY(603, "文件不能为空！"),
    FILE_REPEAT(604, "文件重复上传！"),
    DIR_CREATE_FAILED(611, "目录创建失败！")
    ;

    private Integer code;

    private String msg;

    FileResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
