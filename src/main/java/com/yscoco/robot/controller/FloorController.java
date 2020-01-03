package com.yscoco.robot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.FloorEntity;
import com.yscoco.robot.server.FloorServer;
import com.yscoco.robot.server.RobotServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 10:16
 */
@Slf4j
@RestController
@RequestMapping("floor")
@Api(tags = {"楼层"})
public class FloorController {

    @Autowired
    private FloorServer floorServer;
    @Autowired
    private RobotServer robotServer;


    @ApiOperation(value = "根据楼栋查询楼层信息和机器人数量")
    @PostMapping("findfloorRobot")
    public Message findfloorRobot(Long hid) {
        List<FloorEntity> listFloors = floorServer.pageListFloors(hid);
        for (FloorEntity floorEntity : listFloors) {
            floorEntity.setSumRobot(robotServer.findSumRobotByfid(floorEntity.getId()));
        }
        return Message.success(listFloors);
    }

    @ApiOperation(value = "分页根据楼栋查询楼层信息")
    @PostMapping("findPagefloor")
    public Message findPageFloorByHid(int pageNum, int pageSize, Long hid) {
        PageHelper.startPage(pageNum, pageSize);
        List<FloorEntity> listFloors = floorServer.pageListFloors(hid);
        return Message.success(new PageInfo(listFloors));
    }

    @ApiOperation(value = "删除楼层")
    @PostMapping("deleteFloor")
    public Message deleteByPrimaryKey(@RequestParam Long id) {
        floorServer.deleteByPrimaryKey(id);
        return Message.success();
    }

    @ApiOperation(value = "增加楼层")
    @PostMapping("insertFloor")
    public Message insertFloor(@RequestBody FloorEntity floorEntity) {
        log.info(">>>>>>>>>>>>>>>增加楼层<<<<<<<<<<<<<<<<<<<<<");
        floorServer.insertFloor(floorEntity);
        return Message.success();
    }

    @ApiOperation(value = "修改楼层")
    @PostMapping("updateFloor")
    public Message updateByPrimaryKeySelective(@RequestBody FloorEntity floorEntity) {
        log.info(">>>>>>>>>>>>>>>修改楼层<<<<<<<<<<<<<<<<<<<<<");
        floorServer.updateByPrimaryKeySelective(floorEntity);
        return Message.success();
    }

    @ApiOperation(value = "通过单个id查询楼层")
    @PostMapping("selectFloor")
    public Message selectByPrimaryKey(@RequestParam Long id) {
        log.info(">>>>>>>>>>>>>>>通过单个id查询楼层<<<<<<<<<<<<<<<<<<<<<");
        FloorEntity floorEntity = floorServer.selectByPrimaryKey(id);
        return Message.success(floorEntity);
    }

}
