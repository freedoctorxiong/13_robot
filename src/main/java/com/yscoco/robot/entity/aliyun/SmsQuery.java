package com.yscoco.robot.entity.aliyun;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author: Xiong
 * @Date: 2019/12/4 17:20
 */
@Data
@ToString
public class SmsQuery {
    private String bizId;
    private String phoneNumber;
    private Date sendDate;
    private Long pageSize;
    private Long currentPage;
}
