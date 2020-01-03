package com.yscoco.robot.server.impl;


import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.dao.RoleEntityMapper;
import com.yscoco.robot.dao.UserEntityMapper;
import com.yscoco.robot.dao.UserToRoleEntityMapper;
import com.yscoco.robot.entity.RoleEntity;
import com.yscoco.robot.entity.UserEntity;
import com.yscoco.robot.entity.UserToRoleEntity;
import com.yscoco.robot.server.user.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/10/12 14:19
 */
@Service
public class UserServerImpl implements UserServer {

    @Autowired
    private UserEntityMapper userEntityMapper;
    @Autowired
    private RoleEntityMapper roleEntityMapper;

    @Autowired
    private UserToRoleEntityMapper userToRoleEntityMapper;

    /**
     * 分页查询用户信息
     *
     * @return
     */
    @Override
    public List<UserEntity> findPageUsers(String userName) {
        List<UserEntity> list = userEntityMapper.findPageUsers(userName);
        for (UserEntity userEntity : list) {
            userEntity.setRoles(roleEntityMapper.findByUserId(userEntity.getId()));
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deleteByPrimaryKey(Long id) {
        int result = userEntityMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insert(UserEntity record) {
        record.setCreateTime(new Date());
        int result = userEntityMapper.insert(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertUser(UserEntity record) {
        record.setCreateTime(new Date());
        record.setPassword(DigestUtils.md5DigestAsHex(record.getPassword().getBytes()));
        int result = userEntityMapper.insertSelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
        if (record.getRoles() != null) {
            for (RoleEntity roleEntity : record.getRoles()) {
                UserToRoleEntity userToRoleEntity = new UserToRoleEntity();
                userToRoleEntity.setUserId(record.getId());
                userToRoleEntity.setRoleId(roleEntity.getId());
                userToRoleEntity.setCreateTime(new Date());
                userToRoleEntityMapper.insertSelective(userToRoleEntity);
            }
        }

    }

    @Override
    public UserEntity selectByPrimaryKey(Long id) {
        UserEntity userEntity = userEntityMapper.selectByPrimaryKey(id);
        if (userEntity == null) {
            throw new BizException(Code.NO_DATA);
        }
        return userEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateByPrimaryKeySelective(UserEntity record) {
        int result = userEntityMapper.updateByPrimaryKeySelective(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateByPrimaryKey(UserEntity record) {
        int result = userEntityMapper.updateByPrimaryKey(record);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
    }

    /**
     * 根据电话获得用户
     *
     * @param mobilPhone 电话
     * @return 用户
     */
    @Override
    public UserEntity findByMobilPhone(String mobilPhone) {
        return userEntityMapper.findByMobilPhone(mobilPhone);
    }

    /**
     * 通过电话或者账号查询用户信息
     *
     * @param userinfo
     * @return
     */
    @Override
    public UserEntity findByUserinfo(String userinfo) {
        return userEntityMapper.findByUserinfo(userinfo);
    }


}
