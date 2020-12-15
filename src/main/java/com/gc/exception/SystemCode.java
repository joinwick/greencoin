package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @description system code
 * @createDate 2020/12/10 9:31
 * @since 1.0.0
 */
public enum SystemCode {
    SYSTEM_SUCCESS("B0000"),
    SYSTEM_FAILED("B0001"),

    // data not exist
    DATA_NOT_EXISTS("B0100"),
    PRIVATE_KEY_NOT_EXISTS("B0101"),
    PUBLIC_KEY_NOT_EXISTS("B0102"),
    KEY_NOT_EXISTS("B0103"),
    VALUE_IS_EMPTY("B0104"),

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
    SYSTEM_RESOURCE_FILE_CREATE_FAILED("B0323"),

    // db exception
    DB_EXCEPTION("B0330"),
    DB_INIT_FAILED("B0331"),
    DB_INSERT_FAILED("B0332"),
    DB_UPDATE_FAILED("B0333"),
    DB_DELETE_FAILED("B0334"),
    DB_QUERY_FAILED("B0335"),
    DB_CLOSE_FAILED("B0336"),

    ;

    private final String code;

    SystemCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
