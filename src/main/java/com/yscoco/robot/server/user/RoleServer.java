package com.yscoco.robot.server.user;


import com.yscoco.robot.entity.RoleEntity;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/10/30 16:52
 */
public interface RoleServer {
    int deleteByPrimaryKey(Long id);

    int insert(RoleEntity record);

    int insertSelective(RoleEntity record);

    RoleEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleEntity record);

    int updateByPrimaryKey(RoleEntity record);

    /**
     * 根据用户Id获得角色列表
     *
     * @param userId 用户Id
     * @return 角色列表
     */
    List<RoleEntity> findByUserId(Long userId);

    /**
     * 根据给用户添加角色
     *
     * @param userId
     * @param roleId
     */
    void relateUser(Long userId, Long roleId);

}
