package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @className SystemCode.java
 * @description system code
 * @createDate 2020/12/10 9:31
 * @since 1.0.0
 */
public enum SystemCode {
    SYSTEM_SUCCESS("B0000"),
    SYSTEM_FAILED("B0001"),

    // data not exist
    DATA_NOT_EXISTS("B0100"),

    // system resource exception
    SYSTEM_RESOURCE_EXCEPTION("B0300"),
    SYSTEM_RESOURCE_EXHAUSTION("B0310"),
    SYSTEM_RESOURCE_DISK_EXHAUSTION("B0311"),
    SYSTEM_RESOURCE_MEMORY_EXHAUSTION("B0312"),
    SYSTEM_RESOURCE_FILE_HANDLE_EXHAUSTION("B0313"),
    SYSTEM_RESOURCE_CONNECTION_POOL_EXHAUSTION("B0314"),
    SYSTEM_RESOURCE_THREAD_POOL_EXHAUSTION("B0315"),

    SYSTEM_RESOURCE_ACCESS_FAILED("B0320"),
    SYSTEM_RESOURCE_FILE_READ_FAILED("B0321"),
    SYSTEM_RESOURCE_FILE_WRITE_FAILED("B0322"),

    ;

    private final String code;

    SystemCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
