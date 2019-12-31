package com.yscoco.robot.dao;

import com.yscoco.robot.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

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