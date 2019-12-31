package com.yscoco.robot.dao;

import com.yscoco.robot.entity.RobotEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RobotEntityMapper {

    /**
     * 分页查询
     *
     * @return
     */
    List<RobotEntity> findPageRobot(int type, @Param("robotName") String robotName);


    int deleteByPrimaryKey(Long id);

    int insert(RobotEntity record);

    int insertSelective(RobotEntity record);

    RobotEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RobotEntity record);

    int updateByPrimaryKey(RobotEntity record);
}