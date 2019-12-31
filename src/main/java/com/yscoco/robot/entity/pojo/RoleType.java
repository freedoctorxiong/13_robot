package com.yscoco.robot.entity.pojo;

/**
 * @Author xiong
 * @Date 2019/10/21 15:23
 */
public enum RoleType {
    NOPASSWORD("nopassword"),

    PASSWORD("password");

    // 状态值
    private String code;

    RoleType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
