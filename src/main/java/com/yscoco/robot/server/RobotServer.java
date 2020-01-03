package com.yscoco.robot.server;

import com.yscoco.robot.entity.RobotEntity;
import com.yscoco.robot.entity.RobotEntity;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 9:15
 */
public interface RobotServer {

    void deleteByPrimaryKey(Long id);

    void insert(RobotEntity record);

    void insertSelective(RobotEntity record);

    RobotEntity selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(RobotEntity record);

    void updateByPrimaryKey(RobotEntity record);

    /**
     * 分页查询室内外机器人
     *
     * @return
     */
    List<RobotEntity> findPageRobot(int type, String robotName);

    /**
     * 通过经纬度查询机器人
     *
     * @param longitude
     * @param latitude
     * @param juli
     * @return
     */
    List<RobotEntity> findAreaRobot(double longitude, double latitude, double juli);

    /**
     * 通过楼层查询机器人信息
     * @param fid
     * @return
     */
    List<RobotEntity> findRobotByfid(Long fid);

    /**
     * 通过楼层查询机器人数量
     * @param fid
     * @return
     */
     int findSumRobotByfid(Long fid);

}
