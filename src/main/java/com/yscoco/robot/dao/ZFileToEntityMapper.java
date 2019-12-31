package com.yscoco.robot.dao;

import com.yscoco.robot.entity.ZFileToEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZFileToEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ZFileToEntity record);

    int insertSelective(ZFileToEntity record);

    ZFileToEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ZFileToEntity record);

    int updateByPrimaryKey(ZFileToEntity record);

}