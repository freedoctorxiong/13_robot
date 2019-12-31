package com.yscoco.robot.entity.pojo;

/**
 * @Author: Xiong
 * @Date: 2019/12/12 9:08
 */
public enum EntityType {

    //楼层平面图
    FLOOR("floorEntity"),

    ;

    // 状态值
    private String code;

    EntityType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
