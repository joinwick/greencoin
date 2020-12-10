package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @className BaseException.java
 * @description base exception definition
 * @createDate 2020/12/10 9:13
 * @since 1.0.0
 */
public class BaseException extends RuntimeException{
    private final String exceptionCode;
    private final String exceptionMsg;

    BaseException(String errorCode, String errorMsg){
        this.exceptionCode = errorCode;
        this.exceptionMsg = errorMsg;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "exceptionCode='" + exceptionCode + '\'' +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                '}';
    }
}
