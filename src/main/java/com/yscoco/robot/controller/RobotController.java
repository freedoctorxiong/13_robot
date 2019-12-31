package com.yscoco.robot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.RobotEntity;
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

    @ApiOperation(value = "删除机器人")
    @PostMapping("deleteRobot")
    @ResponseBody
    public Message deleteByPrimaryKey(@RequestParam Long id) {
        robotServer.deleteByPrimaryKey(id);
        return Message.success();
    }

    @ApiOperation(value = "增加机器人")
    @PostMapping("insertRobot")
    @ResponseBody
    public Message insert(@RequestBody RobotEntity robotEntity) {
        log.info(">>>>>>>>>>>>>>>增加机器人<<<<<<<<<<<<<<<<<<<<<");
        robotServer.insertSelective(robotEntity);
        return Message.success();
    }

    @ApiOperation(value = "修改机器人")
    @PostMapping("updateRobot")
    @ResponseBody
    public Message updateByPrimaryKeySelective(@RequestBody RobotEntity robotEntity) {
        log.info(">>>>>>>>>>>>>>>修改机器人<<<<<<<<<<<<<<<<<<<<<");
        robotServer.updateByPrimaryKeySelective(robotEntity);
        return Message.success();
    }

    @ApiOperation(value = "通过单个id查询机器人")
    @PostMapping("selectRobot")
    @ResponseBody
    public Message selectByPrimaryKey(@RequestParam Long id) {
        log.info(">>>>>>>>>>>>>>>通过单个id查询机器人<<<<<<<<<<<<<<<<<<<<<");
        RobotEntity robotEntity = robotServer.selectByPrimaryKey(id);
        return Message.success(robotEntity);
    }

    @ApiOperation(value = "分页查询室内外机器人")
    @PostMapping("findPage")
    @ResponseBody
    public Message findPageRobot(int pageNum, int pageSize, Integer type, String robotName) {
        log.info(">>>>>>>>>>>>>>>分页查询室内外机器人<<<<<<<<<<<<<<<<<<<<<");
        PageHelper.startPage(pageNum, pageSize);
        List<RobotEntity> listRobot = robotServer.findPageRobot(type ,robotName);
        return Message.success(new PageInfo(listRobot));
    }

}
