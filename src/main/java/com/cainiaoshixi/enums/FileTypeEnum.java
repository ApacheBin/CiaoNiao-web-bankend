package com.cainiaoshixi.enums;

public enum FileTypeEnum {

    ROOT(0, "/data/files/"),
    RESUMES(1, "resumes/"),
    STUDENT(2, "student/"),
    COMPANY(3, "company/")
    ;

    private int code;
    private String path;

    FileTypeEnum(int code, String path) {
        this.code = code;
        this.path = path;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
