package com.yscoco.robot.dao;

import com.yscoco.robot.entity.RobotEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RobotEntityMapper {
    /**
     * 通过楼层查询机器人数量
     *
     * @param fid
     * @return
     */
    int findSumRobotByfid(Long fid);

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

    /**
     * @param longitude
     * @param latitude
     * @return
     */
    List<RobotEntity> findAreaRobot(double longitude, double latitude, double juli);

    /**
     * 通过楼层查询机器人
     *
     * @param fid
     * @return
     */
    List<RobotEntity> findRobotByfid(Long fid);


}