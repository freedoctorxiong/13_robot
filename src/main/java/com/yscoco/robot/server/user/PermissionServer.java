package com.yscoco.robot.server.user;


import com.yscoco.robot.entity.PermissionEntity;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/10/30 16:52
 */
public interface PermissionServer {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionEntity record);

    int insertSelective(PermissionEntity record);

    PermissionEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionEntity record);

    int updateByPrimaryKey(PermissionEntity record);

    /**
     * 根据角色Id获得权限列表
     *
     * @param roleId 角色Id
     * @return 权限列表
     */
    List<PermissionEntity> findByRoleId(long roleId);
}
