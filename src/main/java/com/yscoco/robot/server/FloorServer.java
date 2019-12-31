package com.yscoco.robot.server;

import com.yscoco.robot.entity.FloorEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 9:59
 */
public interface FloorServer {

    /**
     * 增加楼层
     * @param floorEntity
     */
    void insertFloor(FloorEntity floorEntity);

    void deleteByPrimaryKey(Long id);

    void insert(FloorEntity record);

    void insertSelective(FloorEntity record);

    FloorEntity selectByPrimaryKey(Long id);

    void updateByPrimaryKeySelective(FloorEntity record);

    void updateByPrimaryKey(FloorEntity record);

    /**
     * 通过楼栋获取楼层总数
     *
     * @return
     */
    int findCountsFloor(Long hid);

    /**
     * 通过楼栋查询楼层
     * @param hid
     * @return
     */
    List<FloorEntity> pageListFloors(Long hid);
}
