package com.yscoco.robot.server.impl;

import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.dao.FloorEntityMapper;
import com.yscoco.robot.dao.HouseEntityMapper;
import com.yscoco.robot.entity.HouseEntity;
import com.yscoco.robot.server.FloorServer;
import com.yscoco.robot.server.HouseServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 9:37
 */
@Slf4j
@Service
public class HouseServerImpl implements HouseServer {

    @Autowired
    private HouseEntityMapper houseEntityMapper;
    @Autowired
    private FloorEntityMapper floorEntityMapper;


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByPrimaryKey(Long id) {
        int result = houseEntityMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)

    public void insert(HouseEntity record) {
        int result = houseEntityMapper.insert(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)

    public void insertSelective(HouseEntity record) {
        record.setCreateTime(new Date());
        int result = houseEntityMapper.insertSelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public HouseEntity selectByPrimaryKey(Long id) {
        return houseEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)

    public void updateByPrimaryKeySelective(HouseEntity record) {
        int result = houseEntityMapper.updateByPrimaryKeySelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateByPrimaryKey(HouseEntity record) {
        int result = houseEntityMapper.updateByPrimaryKey(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public List<HouseEntity> findPageHouse(String houseName) {
        List<HouseEntity> listHouse = houseEntityMapper.findPageHouse(houseName);
        if (listHouse.size() != 0) {
            for (HouseEntity houseEntity : listHouse) {
                int counts = floorEntityMapper.findCountsFloor(houseEntity.getId());
                houseEntity.setFloorSum(counts);
            }
        }
        return listHouse;
    }
}
