package com.yscoco.robot.dao;

import com.yscoco.robot.entity.HouseEntity;
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
}