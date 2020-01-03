package com.yscoco.robot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Date;

@Getter
@Setter
public class FloorEntity {
    private Long id;

    private String floorName;

    private Double imgHeight;

    private Double imgWidth;

    private Long hid;

    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Integer state;
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;


    @Transient
    private Long fileId;

    @Transient
    @ApiModelProperty(hidden = true)
    private int sumRobot;


    public FloorEntity(Long id, String floorName, Double imgHeight, Double imgWidth, Date createTime, Integer state, Date modifyTime, Long hid) {
        this.id = id;
        this.floorName = floorName;
        this.imgHeight = imgHeight;
        this.imgWidth = imgWidth;
        this.createTime = createTime;
        this.state = state;
        this.modifyTime = modifyTime;
        this.hid = hid;
    }


}