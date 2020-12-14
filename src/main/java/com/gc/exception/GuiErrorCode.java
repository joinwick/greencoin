package com.gc.exception;

/**
 * @author join wick
 * @version 1.0.0
 * @description gui error code detail definition
 * @createDate 2020/12/10 13:26
 * @since 1.0.0
 */
public enum GuiErrorCode implements ErrorCode{
    GUI_SUCCESS(GuiCode.GUI_SUCCESS, "GUI运行正常"),
    GUI_FAILED(GuiCode.GUI_FAILED, "用户端运行异常"),

    // user register exception
    USER_REGISTER_FAILED(GuiCode.USER_REGISTER_FAILED, "用户注册错误"),
    USER_AGREEMENT_CHECK_FAILED(GuiCode.USER_AGREEMENT_CHECK_FAILED, "用户未同意隐私协议"),
    USER_REGISTER_CONDITION_CHECK_FAILED(GuiCode.USER_REGISTER_CONDITION_CHECK_FAILED, "注册国家或地区受限"),

    USER_ACCOUNT_CHECK_FAILED(GuiCode.USER_ACCOUNT_CHECK_FAILED, "用户账号校验失败"),
    USER_ACCOUNT_EXISTS(GuiCode.USER_ACCOUNT_EXISTS, "用户账号已存在"),
    USER_ACCOUNT_CONTAINS_SENSITIVE_CHARACTER(GuiCode.USER_ACCOUNT_CONTAINS_SENSITIVE_CHARACTER, "用户账号包含敏感词"),
    USER_ACCOUNT_CONTAINS_SPECIAL_CHARACTER(GuiCode.USER_ACCOUNT_CONTAINS_SPECIAL_CHARACTER, "用户账号包含特殊字符"),

    USER_PASSWORD_CHECK_CHARACTER(GuiCode.USER_PASSWORD_CHECK_CHARACTER, "密码校验失败"),
    USER_PASSWORD_LENGTH_CHECK_FAILED(GuiCode.USER_PASSWORD_LENGTH_CHECK_FAILED, "密码长度不够"),
    USER_PASSWORD_INTENSITY_CHECK_FAILED(GuiCode.USER_PASSWORD_INTENSITY_CHECK_FAILED, "密码强度不够"),

    VALIDATION_CODE_CHECK_FAILED(GuiCode.VALIDATION_CODE_CHECK_FAILED, "验证码校验失败"),
    VALIDATION_CODE_MESSAGE_CHECK_FAILED(GuiCode.VALIDATION_CODE_MESSAGE_CHECK_FAILED, "短信验证码输入错误"),
    VALIDATION_CODE_EMAIL_CHECK_FAILED(GuiCode.VALIDATION_CODE_EMAIL_CHECK_FAILED, "邮箱验证码输入错误"),
    VALIDATION_CODE_VOICE_CHECK_FAILED(GuiCode.VALIDATION_CODE_VOICE_CHECK_FAILED, "语音验证码输入错误"),

    USER_IDENTIFICATION_CHECK_FAILED(GuiCode.USER_IDENTIFICATION_CHECK_FAILED, "用户证件异常"),
    USER_IDENTIFICATION_TYPE_CHECK_FAILED(GuiCode.USER_IDENTIFICATION_TYPE_CHECK_FAILED, "不支持的用户证件类型"),

    // user login exception
    USER_LOGIN_FAILED(GuiCode.USER_LOGIN_FAILED, "用户登录异常"),
    USER_LOGIN_ACCOUNT_NOT_EXISTS(GuiCode.USER_LOGIN_ACCOUNT_NOT_EXISTS, "用户账号不存在"),
    USER_LOGIN_ACCOUNT_FORBIDDEN(GuiCode.USER_LOGIN_ACCOUNT_FORBIDDEN, "用户账号已冻结"),
    USER_LOGIN_ACCOUNT_DESTROY(GuiCode.USER_LOGIN_ACCOUNT_DESTROY, "用户账号已作废"),

    USER_LOGIN_PASSWORD_CHECK_FAILED(GuiCode.USER_LOGIN_PASSWORD_CHECK_FAILED, "用户密码错误"),
    USER_LOGIN_PASSWORD_ENTRY_LIMITED(GuiCode.USER_LOGIN_PASSWORD_ENTRY_LIMITED, "用户输入密码次数超限"),
    ;

    private final GuiCode errorCode;
    private final String errorMsg;

    GuiErrorCode(GuiCode errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode.getCode();
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
