package com.gc.utils;

/**
 * @author luo.changshu
 * @version 1.0
 * @date 2020/11/26 20:49
 * @description Self Define Run Time Exception
 * @since 1.0
 */
public class SelfDefineRunTimeException extends RuntimeException{
    public SelfDefineRunTimeException(String message){
        super(message);
    }
}
