package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @className GCException.java
 * @description gc exception definition
 * @createDate 2020/12/10 9:16
 * @since 1.0.0
 */
public class GCException extends BaseException{
    private static final Long serialVersionUID = 1L;
    private String exceptionCode;
    private String exceptionMsg;

    GCException(){
        super("", "");
    }

    GCException(String exceptionCode, String exceptionMsg) {
        super(exceptionCode, exceptionMsg);
    }

    GCException(ErrorCode errorCode){
        super(errorCode.getErrorCode(), errorCode.getErrorMsg());
    }

    @Override
    public String getExceptionCode() {
        return exceptionCode;
    }

    @Override
    public String getExceptionMsg() {
        return exceptionMsg;
    }
}
