package com.yscoco.robot.server.impl;



import com.yscoco.robot.dao.RoleEntityMapper;
import com.yscoco.robot.entity.RoleEntity;
import com.yscoco.robot.server.user.RoleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/10/12 16:16
 */
@Service
public class RoleServerImpl implements RoleServer {


    @Autowired
    private RoleEntityMapper roleEntityMapper;


    /**
     * 根据用户Id获得角色列表
     *
     * @param userId 用户Id
     * @return 角色列表
     */
    @Override
    public List<RoleEntity> findByUserId(Long userId) {
        return roleEntityMapper.findByUserId(userId);
    }


    @Override
    public int deleteByPrimaryKey(Long id) {
        return roleEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RoleEntity record) {
        return roleEntityMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleEntity record) {
        return roleEntityMapper.insertSelective(record);
    }


    @Override
    public RoleEntity selectByPrimaryKey(Long id) {
        return roleEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleEntity record) {
        return roleEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleEntity record) {
        return roleEntityMapper.updateByPrimaryKey(record);
    }

}
