package com.yscoco.robot.dao;

import com.yscoco.robot.entity.HouseEntity;
import com.yscoco.robot.entity.RobotEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HouseEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HouseEntity record);

    int insertSelective(HouseEntity record);

    HouseEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HouseEntity record);

    int updateByPrimaryKey(HouseEntity record);

    List<HouseEntity> findPageHouse(@Param(value = "houseName") String houseName);

    /**
     * 通过经纬度查询房屋
     *
     * @param longitude
     * @param latitude
     * @return
     */
    List<HouseEntity> findAreaHouse(double longitude, double latitude, double juli);
}