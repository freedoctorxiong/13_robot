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
    List<RobotEntity> findPageRobot(int type,String robotName);

}
