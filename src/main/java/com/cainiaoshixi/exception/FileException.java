package com.cainiaoshixi.exception;

import com.cainiaoshixi.enums.FileResultEnum;

public class FileException extends RuntimeException{
    private Integer code;

    public FileException(FileResultEnum fileResultEnum) {
        super(fileResultEnum.getMsg());
        this.code = fileResultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
