package com.yscoco.robot.server;

import com.yscoco.robot.entity.HouseEntity;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 9:35
 */
public interface HouseServer {
    void deleteByPrimaryKey(Long id);

    void insert(HouseEntity record);

    void insertSelective(HouseEntity record);

    HouseEntity selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(HouseEntity record);

    void updateByPrimaryKey(HouseEntity record);

    /**
     * 分页查询
     *
     * @return
     */
    List<HouseEntity> findPageHouse(String houseName);

}
