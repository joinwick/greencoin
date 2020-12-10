package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @className CallableErrorCode.java
 * @description callable error code detail definition
 * @createDate 2020/12/10 13:39
 * @since 1.0.0
 */
public enum CallableErrorCode implements ErrorCode{
    CALLABLE_SUCCESS(CallableCode.CALLABLE_SUCCESS, "调用第三方服务正常"),
    CALLABLE_FAILED(CallableCode.CALLABLE_FAILED, "调用第三方服务失败"),

    // component service exception
    COMPONENT_SERVICE_FAILED(CallableCode.COMPONENT_SERVICE_FAILED, "中间件服务出错"),
    RPC_SERVICE_FAILED(CallableCode.RPC_SERVICE_FAILED, "RPC服务出错"),
    RPC_SERVICE_NOT_EXISTS(CallableCode.RPC_SERVICE_NOT_EXISTS, "RPC服务未找到"),
    RPC_SERVICE_NOT_REGISTER(CallableCode.RPC_SERVICE_NOT_REGISTER, "RPC服务未注册"),
    INTERFACE_NOT_EXISTS(CallableCode.INTERFACE_NOT_EXISTS, "接口不存在"),

    MESSAGE_SERVICE_FAILED(CallableCode.MESSAGE_SERVICE_FAILED, "消息服务出错"),
    MESSAGE_POST_FAILED(CallableCode.MESSAGE_POST_FAILED, "消息投递出错"),
    MESSAGE_CONSUME_FAILED(CallableCode.MESSAGE_CONSUME_FAILED, "消息消费出错"),
    MESSAGE_SUBSCRIBE_FAILED(CallableCode.MESSAGE_SUBSCRIBE_FAILED, "消息订阅出错"),
    MESSAGE_GROUP_NOT_EXISTS(CallableCode.MESSAGE_GROUP_NOT_EXISTS, "消息分组未查到"),

    CACHE_SERVICE_FAILED(CallableCode.CACHE_SERVICE_FAILED, "缓存服务出错"),
    KEY_LENGTH_EXCEED_LIMIT(CallableCode.KEY_LENGTH_EXCEED_LIMIT, "Key长度超过限制"),
    VALUE_LENGTH_EXCEED_LIMIT(CallableCode.VALUE_LENGTH_EXCEED_LIMIT, "Value长度超过限制"),
    CACHE_OVERFLOW(CallableCode.CACHE_OVERFLOW, "存储容量已满"),
    DATA_FORMAT_UNSUPPORTED(CallableCode.DATA_FORMAT_UNSUPPORTED, "不支持的数据格式"),

    CONFIGURATION_SERVICE_FAILED(CallableCode.CONFIGURATION_SERVICE_FAILED, "配置服务出错"),

    NETWORK_SERVICE_FAILED(CallableCode.NETWORK_SERVICE_FAILED, "网络资源服务出错"),
    VPN_SERVICE_FAILED(CallableCode.VPN_SERVICE_FAILED, "VPN服务出错"),
    CDN_SERVICE_FAILED(CallableCode.CDN_SERVICE_FAILED, "CDN服务出错"),
    DNS_SERVICE_FAILED(CallableCode.DNS_SERVICE_FAILED, "DNS服务出错"),
    GATEWAY_SERVICE_FAILED(CallableCode.GATEWAY_SERVICE_FAILED, "网关服务出错"),
    NETWORK_DISCONNECT(CallableCode.NETWORK_DISCONNECT, "网络未连接"),
    ;
    private final CallableCode errorCode;
    private final String errorMsg;


    CallableErrorCode(CallableCode errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode.getCode();
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
