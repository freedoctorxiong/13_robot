package com.yscoco.robot.server.impl;

import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.dao.FloorEntityMapper;
import com.yscoco.robot.dao.ZFileEntityMapper;
import com.yscoco.robot.dao.ZFileToEntityMapper;
import com.yscoco.robot.entity.FloorEntity;
import com.yscoco.robot.entity.ZFileToEntity;
import com.yscoco.robot.entity.pojo.EntityType;
import com.yscoco.robot.server.FloorServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 10:00
 */
@Service
public class FloorServerImpl implements FloorServer {

    @Autowired
    private FloorEntityMapper floorEntityMapper;

    @Autowired
    private ZFileToEntityMapper zFileToEntityMapper;

    /**
     * 获取楼层总数
     *
     * @param hid
     * @return
     */
    @Override
    public int findCountsFloor(Long hid) {
        return floorEntityMapper.findCountsFloor(hid);
    }

    @Override
    public List<FloorEntity> pageListFloors(Long hid) {

        return floorEntityMapper.pageListFloors(hid);
    }

    /**
     * 增加楼层
     *
     * @param floorEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertFloor(FloorEntity floorEntity) {

        int result = floorEntityMapper.insertSelective(floorEntity);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
        ZFileToEntity zFileToEntity = new ZFileToEntity();
        zFileToEntity.setEntityid(floorEntity.getId());
        zFileToEntity.setFileid(floorEntity.getFileId());
        zFileToEntity.setType("ichnography");
        zFileToEntity.setEntityType(EntityType.FLOOR.getCode());
        int res = zFileToEntityMapper.insertSelective(zFileToEntity);
        if (res != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByPrimaryKey(Long id) {
        int result = floorEntityMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insert(FloorEntity record) {
        int result = floorEntityMapper.insert(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertSelective(FloorEntity record) {
        int result = floorEntityMapper.insertSelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public FloorEntity selectByPrimaryKey(Long id) {
        return floorEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateByPrimaryKeySelective(FloorEntity record) {
        int result = floorEntityMapper.updateByPrimaryKeySelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateByPrimaryKey(FloorEntity record) {
        int result = floorEntityMapper.updateByPrimaryKey(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }
}
