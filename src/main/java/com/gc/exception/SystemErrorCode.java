package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @description system error code detail definition
 * @createDate 2020/12/10 13:11
 * @since 1.0.0
 */
public enum SystemErrorCode implements ErrorCode {
    SYSTEM_SUCCESS(SystemCode.SYSTEM_SUCCESS, "系统运行成功"),
    SYSTEM_FAILED(SystemCode.SYSTEM_FAILED, "系统运行失败"),

    // data not exist
    DATA_NOT_EXISTS(SystemCode.DATA_NOT_EXISTS, "数据不存在"),
    PRIVATE_KEY_NOT_EXISTS(SystemCode.PRIVATE_KEY_NOT_EXISTS, "用户私钥不存在"),
    PUBLIC_KEY_NOT_EXISTS(SystemCode.PUBLIC_KEY_NOT_EXISTS, "用户公钥不存在"),
    SYMMETRIC_KEY_NOT_EXISTS(SystemCode.SYMMETRIC_KEY_NOT_EXISTS, "用户对称密钥不存在"),
    KEY_NOT_EXISTS(SystemCode.KEY_NOT_EXISTS, "key不存在"),
    VALUE_IS_EMPTY(SystemCode.VALUE_IS_EMPTY, "value值为空"),

    // system resource exception
    SYSTEM_RESOURCE_EXCEPTION(SystemCode.SYSTEM_RESOURCE_EXCEPTION, "系统资源异常"),
    SYSTEM_RESOURCE_EXHAUSTION(SystemCode.SYSTEM_RESOURCE_EXHAUSTION, "系统资源耗尽"),
    SYSTEM_RESOURCE_DISK_EXHAUSTION(SystemCode.SYSTEM_RESOURCE_DISK_EXHAUSTION, "系统磁盘空间耗尽"),
    SYSTEM_RESOURCE_MEMORY_EXHAUSTION(SystemCode.SYSTEM_RESOURCE_MEMORY_EXHAUSTION, "系统内存耗尽"),
    SYSTEM_RESOURCE_FILE_HANDLE_EXHAUSTION(SystemCode.SYSTEM_RESOURCE_FILE_HANDLE_EXHAUSTION, "系统句柄耗尽"),
    SYSTEM_RESOURCE_CONNECTION_POOL_EXHAUSTION(SystemCode.SYSTEM_RESOURCE_CONNECTION_POOL_EXHAUSTION, "系统连接池耗尽"),
    SYSTEM_RESOURCE_THREAD_POOL_EXHAUSTION(SystemCode.SYSTEM_RESOURCE_THREAD_POOL_EXHAUSTION, "系统线程池耗尽"),

    SYSTEM_RESOURCE_ACCESS_FAILED(SystemCode.SYSTEM_RESOURCE_ACCESS_FAILED, "系统资源访问异常"),
    SYSTEM_RESOURCE_FILE_READ_FAILED(SystemCode.SYSTEM_RESOURCE_FILE_READ_FAILED, "系统读取磁盘文件失败"),
    SYSTEM_RESOURCE_FILE_WRITE_FAILED(SystemCode.SYSTEM_RESOURCE_FILE_WRITE_FAILED, "系统写入磁盘文件失败"),
    SYSTEM_RESOURCE_FILE_CREATE_FAILED(SystemCode.SYSTEM_RESOURCE_FILE_CREATE_FAILED, "系统创建文件失败"),

    DB_EXCEPTION(SystemCode.DB_EXCEPTION, "数据库异常"),
    DB_INIT_FAILED(SystemCode.DB_INIT_FAILED, "数据库启动异常"),
    DB_INSERT_FAILED(SystemCode.DB_INSERT_FAILED, "数据插入异常"),
    DB_UPDATE_FAILED(SystemCode.DB_UPDATE_FAILED, "数据更新异常"),
    DB_DELETE_FAILED(SystemCode.DB_DELETE_FAILED, "数据删除异常"),
    DB_QUERY_FAILED(SystemCode.DB_QUERY_FAILED, "数据查询异常"),
    DB_CLOSE_FAILED(SystemCode.DB_CLOSE_FAILED, "数据库关闭异常");


    private final SystemCode errorCode;
    private final String errorMsg;

    SystemErrorCode(SystemCode exceptionCode, String exceptionMsg) {
        this.errorCode = exceptionCode;
        this.errorMsg = exceptionMsg;
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
