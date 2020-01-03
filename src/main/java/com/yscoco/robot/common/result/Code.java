package com.yscoco.robot.common.result;

public enum Code {
    SUCCESS("0", "成功"),
    ERROR("1", "失败"),

    SERVER_ERROR_MSG("100", "未知错误"),
    NOT_PARAM("101", "缺少必要参数"),
    OPERATION_FAILURE_ERROR("102", "操作异常：数据库操作失败"),
    UNKNOWCLASS("103","未知数据类型"),
    INVALID_PARAMETER("104", "参数不合法"),



    NO_SELECT_FILE("1001", "上传失败，请选择文件"),
    NO_DATA("1002", "无记录"),


    NO_LOGIN_ERROR("10001","用户异常：未登录"),

    PASSWORD_ERROR("10002","用户异常：密码错误"),

    USER_DISABLED_ERROR("10003","用户异常：该用户已被禁用"),

    MOBILE_ERROR("10004","用户异常：手机号不合法"),

    USER_NOT_EXIST_ERROR("10005","用户异常：该用户不存在"),


    SMS_SEND_ERROR("20001","短信验证码异常：短信验证码发送失败"),

    VCODE_FAILURE("20002","短信验证码异常：验证码失效"),

    VCODE_ERROR("20003","短信验证码异常：验证码错误"),

    VCODE_LIMIT_ERROR("20004","短信验证码异常：获取短信验证码限制"),


            ;

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Code(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    @Override
    public String toString() {
        return code;
    }
    }
