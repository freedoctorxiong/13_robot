package com.yscoco.robot.entity.aliyun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Xiong
 * @Date: 2019/10/16 14:49
 */
@Getter
@Setter
@ApiModel(value = "手机绑定、校验实体")
public class BindInfo {

    /*手机号*/
    @ApiModelProperty(value = "手机号")
    private String tellphone;
    /*验证码*/
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "用户id")
    private long uId;
}
