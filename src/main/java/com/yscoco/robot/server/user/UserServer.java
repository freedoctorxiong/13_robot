package com.yscoco.robot.server.user;


import com.yscoco.robot.entity.UserEntity;

/**
 * @Author: Xiong
 * @Date: 2019/10/30 16:51
 */
public interface UserServer {

    void deleteByPrimaryKey(Long id);

    void insert(UserEntity record);

    void insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(UserEntity record);

    void updateByPrimaryKey(UserEntity record);

    /**
     * 根据电话获得用户
     *
     * @param mobilPhone 电话
     * @return 用户
     */
    UserEntity findByMobilPhone(String mobilPhone);

    /**
     * 通过电话或者账号查询用户信息
     *
     * @param userinfo
     * @return
     */
    UserEntity findByUserinfo(String userinfo);


}
