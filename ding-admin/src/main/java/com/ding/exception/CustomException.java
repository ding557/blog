package com.ding.exception;
/**
 * @ClassName 丁557
 * @DATE 2022/3/22 20:36
 * @Auther 73660
 * @Description  返回值
 * @Version 1.0
 */

public class CustomException extends RuntimeException {
    private String code;
    private String msg;

    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
