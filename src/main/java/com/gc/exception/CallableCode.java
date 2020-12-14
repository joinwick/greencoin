package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @description callable code definition
 * @createDate 2020/12/10 10:49
 * @since 1.0.0
 */
public enum CallableCode {
    CALLABLE_SUCCESS("C0000"),
    CALLABLE_FAILED("C0001"),

    // component service exception
    COMPONENT_SERVICE_FAILED("C0100"),
    RPC_SERVICE_FAILED("C0110"),
    RPC_SERVICE_NOT_EXISTS("C0111"),
    RPC_SERVICE_NOT_REGISTER("C0112"),
    INTERFACE_NOT_EXISTS("C0113"),

    MESSAGE_SERVICE_FAILED("C0120"),
    MESSAGE_POST_FAILED("C0121"),
    MESSAGE_CONSUME_FAILED("C0122"),
    MESSAGE_SUBSCRIBE_FAILED("C0123"),
    MESSAGE_GROUP_NOT_EXISTS("C0124"),

    CACHE_SERVICE_FAILED("C0130"),
    KEY_LENGTH_EXCEED_LIMIT("C0131"),
    VALUE_LENGTH_EXCEED_LIMIT("C0132"),
    CACHE_OVERFLOW("C0133"),
    DATA_FORMAT_UNSUPPORTED("C0134"),

    CONFIGURATION_SERVICE_FAILED("C0140"),
    NETWORK_SERVICE_FAILED("C0150"),
    VPN_SERVICE_FAILED("C0151"),
    CDN_SERVICE_FAILED("C0152"),
    DNS_SERVICE_FAILED("C0153"),
    GATEWAY_SERVICE_FAILED("C0154"),
    NETWORK_DISCONNECT("C0155"),
    ;

    private final String code;

    CallableCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
