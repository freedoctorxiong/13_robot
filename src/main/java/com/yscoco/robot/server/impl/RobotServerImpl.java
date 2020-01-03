package com.yscoco.robot.server.impl;

import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.dao.RobotEntityMapper;
import com.yscoco.robot.entity.RobotEntity;
import com.yscoco.robot.server.RobotServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 15:43
 */
@Service
public class RobotServerImpl implements RobotServer {

    @Autowired
    private RobotEntityMapper robotEntityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByPrimaryKey(Long id) {
        int result = robotEntityMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public void insert(RobotEntity record) {
        record.setCreateTime(new Date());
        int result = robotEntityMapper.insert(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public void insertSelective(RobotEntity record) {
        record.setCreateTime(new Date());
        int result = robotEntityMapper.insertSelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public RobotEntity selectByPrimaryKey(Long id) {
        return robotEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(RobotEntity record) {
        int result = robotEntityMapper.updateByPrimaryKeySelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public void updateByPrimaryKey(RobotEntity record) {
        int result = robotEntityMapper.updateByPrimaryKey(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    /**
     * 分页查询室内外机器人
     *
     * @param type
     * @return
     */
    @Override
    public List<RobotEntity> findPageRobot(int type, String robotName) {

        return robotEntityMapper.findPageRobot(type, robotName);
    }

    /**
     * 通过经纬度查询机器人
     *
     * @param longitude
     * @param latitude
     * @param juli
     * @return
     */
    @Override
    public List<RobotEntity> findAreaRobot(double longitude, double latitude, double juli) {
        return robotEntityMapper.findAreaRobot(longitude, latitude, juli);
    }

    /**
     * 通过楼层查询机器人
     *
     * @param fid
     * @return
     */
    @Override
    public List<RobotEntity> findRobotByfid(Long fid) {
        return robotEntityMapper.findRobotByfid(fid);
    }

    /**
     * 通过楼层查询机器人数量
     *
     * @param fid
     * @return
     */
    @Override
    public int findSumRobotByfid(Long fid) {
        return robotEntityMapper.findSumRobotByfid(fid);
    }
}
