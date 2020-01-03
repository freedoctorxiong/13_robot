package com.yscoco.robot.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.HouseEntity;
import com.yscoco.robot.entity.RobotEntity;
import com.yscoco.robot.server.HouseServer;
import com.yscoco.robot.server.RobotServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 15:40
 */

@Slf4j
@RestController
@RequestMapping("robot")
@Api(tags = {"机器人"})
public class RobotController {

    @Autowired
    private RobotServer robotServer;
    @Autowired
    private HouseServer houseServer;

    @ApiOperation(value = "根据楼层查询机器人")
    @PostMapping("Robotfid")
    public Message findRobotByfid(@RequestParam Long fid) {
        List<RobotEntity> listrobot = robotServer.findRobotByfid(fid);
        return Message.success(listrobot);
    }

    @ApiOperation(value = "删除机器人")
    @PostMapping("deleteRobot")
    public Message deleteByPrimaryKey(@RequestParam Long id) {
        robotServer.deleteByPrimaryKey(id);
        return Message.success();
    }

    @ApiOperation(value = "增加机器人")
    @PostMapping("insertRobot")
    public Message insert(@RequestBody RobotEntity robotEntity) {
        log.info(">>>>>>>>>>>>>>>增加机器人<<<<<<<<<<<<<<<<<<<<<");
        robotServer.insertSelective(robotEntity);
        return Message.success();
    }

    @ApiOperation(value = "修改机器人")
    @PostMapping("updateRobot")
    public Message updateByPrimaryKeySelective(@RequestBody RobotEntity robotEntity) {
        log.info(">>>>>>>>>>>>>>>修改机器人<<<<<<<<<<<<<<<<<<<<<");
        robotServer.updateByPrimaryKeySelective(robotEntity);
        return Message.success();
    }

    @ApiOperation(value = "通过单个id查询机器人")
    @PostMapping("selectRobot")
    public Message selectByPrimaryKey(@RequestParam Long id) {
        log.info(">>>>>>>>>>>>>>>通过单个id查询机器人<<<<<<<<<<<<<<<<<<<<<");
        RobotEntity robotEntity = robotServer.selectByPrimaryKey(id);
        return Message.success(robotEntity);
    }

    @ApiOperation(value = "分页查询室内外机器人")
    @PostMapping("findPage")
    public Message findPageRobot(int pageNum, int pageSize, Integer type, String robotName) {
        log.info(">>>>>>>>>>>>>>>分页查询室内外机器人<<<<<<<<<<<<<<<<<<<<<");
        PageHelper.startPage(pageNum, pageSize);
        List<RobotEntity> listRobot = robotServer.findPageRobot(type, robotName);
        return Message.success(new PageInfo(listRobot));
    }

    @ApiOperation(value = "根据经纬度查询可用机器人")
    @PostMapping("findAreaRobot")
    public Message findAreaRobot(double longitude, double latitude) {
        log.info(">>>>>>>>>>>>>>>根据经纬度查询可用机器人<<<<<<<<<<<<<<<<<<<<<");
        JSONObject jsonObject = new JSONObject();
        double juli = 50000;
        List<RobotEntity> listRobot = robotServer.findAreaRobot(longitude, latitude, juli);

        List<HouseEntity> listHouse = houseServer.findAreaHouse(longitude, latitude, juli);
        jsonObject.put("robot", listRobot);
        jsonObject.put("house", listHouse);
        return Message.success(jsonObject);
    }

}
