package com.yscoco.robot.dao;

import com.yscoco.robot.entity.FloorEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FloorEntityMapper {
    /**
     * 通过楼栋查询楼层
     * @param hid
     * @return
     */
    List<FloorEntity> pageListFloors(Long hid);

    int deleteByPrimaryKey(Long id);

    int insert(FloorEntity record);

    int insertSelective(FloorEntity record);

    FloorEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FloorEntity record);

    int updateByPrimaryKey(FloorEntity record);

    /**
     * 获取楼层总数
     *
     * @return
     */
    int findCountsFloor(Long hid);
}