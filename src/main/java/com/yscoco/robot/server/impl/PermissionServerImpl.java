package com.yscoco.robot.server.impl;



import com.yscoco.robot.dao.PermissionEntityMapper;
import com.yscoco.robot.entity.PermissionEntity;
import com.yscoco.robot.server.user.PermissionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/10/12 16:16
 */
@Service
public class PermissionServerImpl implements PermissionServer {


    @Autowired
    private PermissionEntityMapper permissionEntityMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionEntityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PermissionEntity record) {
        return permissionEntityMapper.insert(record);
    }

    /**
     * 根据角色Id获得权限列表
     *
     * @param roleId 角色Id
     * @return 权限列表
     */
    @Override
    public List<PermissionEntity> findByRoleId(long roleId) {
        return permissionEntityMapper.findByRoleId(roleId);
    }

    @Override
    public int insertSelective(PermissionEntity record) {
        return permissionEntityMapper.insertSelective(record);
    }

    @Override
    public PermissionEntity selectByPrimaryKey(Long id) {
        return permissionEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PermissionEntity record) {
        return permissionEntityMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PermissionEntity record) {
        return updateByPrimaryKey(record);
    }

}
