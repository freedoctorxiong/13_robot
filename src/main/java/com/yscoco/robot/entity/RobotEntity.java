package com.yscoco.robot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RobotEntity {

    private Long id;
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
    @ApiModelProperty(value = "机器人名称")
    private String robotName;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = "识别码")
    private String idCode;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "设备id")
    private String deviceId;

    private Long fid;
    @ApiModelProperty(value = "x轴")
    private Double xCoordinate;
    @ApiModelProperty(value = "y轴")
    private Double yCoordinate;

    @ApiModelProperty(value = "0--室内，1--室外")
    private Integer type;


    @Transient
    @ApiModelProperty(hidden = true)
    private String floorName;
    @Transient
    @ApiModelProperty(hidden = true)
    private String houseName;

    public RobotEntity(Long id, Date createTime, Integer state, Date modifyTime, String robotName, String longitude, String latitude, String idCode, String password, String deviceId, Long fid, Double xCoordinate, Double yCoordinate, Integer type) {
        this.id = id;
        this.createTime = createTime;
        this.state = state;
        this.modifyTime = modifyTime;
        this.robotName = robotName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.idCode = idCode;
        this.password = password;
        this.deviceId = deviceId;
        this.fid = fid;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName == null ? null : robotName.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode == null ? null : idCode.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}