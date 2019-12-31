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
@EntityListeners(AuditingEntityListener.class)
public class FloorEntity {
    private Long id;

    private String floorName;

    private Double imgHeight;

    private Double imgWidth;

    private Long hid;

    @CreatedDate
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private Integer state;
    @LastModifiedDate
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;


    @Transient
    private Long fileId;



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

    public FloorEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName == null ? null : floorName.trim();
    }

    public Double getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(Double imgHeight) {
        this.imgHeight = imgHeight;
    }

    public Double getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Double imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }
}