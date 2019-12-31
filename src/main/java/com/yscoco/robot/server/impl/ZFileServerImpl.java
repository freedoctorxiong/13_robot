package com.yscoco.robot.server.impl;


import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.dao.ZFileEntityMapper;
import com.yscoco.robot.entity.ZFileEntity;
import com.yscoco.robot.entity.pojo.EntityType;
import com.yscoco.robot.server.file.ZFileServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: Xiong
 * @Date: 2019/12/5 18:45
 */
@Service
public class ZFileServerImpl implements ZFileServer {

    @Autowired
    private ZFileEntityMapper zFileEntityMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByPrimaryKey(Long id) {
        int result = zFileEntityMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insert(ZFileEntity record) {
        int result = zFileEntityMapper.insert(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertSelective(ZFileEntity record) {
        int result = zFileEntityMapper.insertSelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    public ZFileEntity selectByPrimaryKey(Long id) {
        ZFileEntity zFileEntity = zFileEntityMapper.selectByPrimaryKey(id);
        if (zFileEntity == null) {
            throw new BizException(Code.NO_DATA);
        }
        return zFileEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateByPrimaryKeySelective(ZFileEntity record) {
        int result = zFileEntityMapper.updateByPrimaryKeySelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateByPrimaryKey(ZFileEntity record) {
        int result = zFileEntityMapper.updateByPrimaryKey(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    /**
     * 通过实体类id和类型查询文件信息
     *
     * @param entityId
     * @param entityType
     * @return
     */
    @Override
    public ZFileEntity selectFileByEntity(Long entityId, EntityType entityType) {
        ZFileEntity zFileEntity = zFileEntityMapper.selectFileByEntity(entityId, entityType.getCode());
        if (zFileEntity == null) {
            throw new BizException(Code.NO_DATA);
        }
        return zFileEntity;
    }
}
