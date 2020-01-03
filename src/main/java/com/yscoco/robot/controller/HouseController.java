package com.yscoco.robot.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.HouseEntity;
import com.yscoco.robot.server.HouseServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/31 9:40
 */
@Slf4j
@RestController
@RequestMapping("house")
@Api(tags = {"楼栋"})
public class HouseController {

    @Autowired
    private HouseServer houseServer;

    @ApiOperation(value = "删除楼栋")
    @PostMapping("deleteHouse")
    public Message deleteByPrimaryKey(@RequestParam Long id) {
        houseServer.deleteByPrimaryKey(id);
        return Message.success();
    }

    @ApiOperation(value = "增加楼栋")
    @PostMapping("insertHouse")
    public Message insert(@RequestBody HouseEntity houseEntity) {
        log.info(">>>>>>>>>>>>>>>增加楼栋<<<<<<<<<<<<<<<<<<<<<");
        houseServer.insertSelective(houseEntity);
        return Message.success();
    }

    @ApiOperation(value = "修改楼栋")
    @PostMapping("updateHouse")
    public Message updateByPrimaryKeySelective(@RequestBody HouseEntity houseEntity) {
        log.info(">>>>>>>>>>>>>>>修改楼栋<<<<<<<<<<<<<<<<<<<<<");
        houseServer.updateByPrimaryKeySelective(houseEntity);
        return Message.success();
    }

    @ApiOperation(value = "通过单个id查询楼栋")
    @PostMapping("selectHouse")
    public Message selectByPrimaryKey(@RequestParam Long id) {
        log.info(">>>>>>>>>>>>>>>通过单个id查询楼栋<<<<<<<<<<<<<<<<<<<<<");
        HouseEntity houseEntity = houseServer.selectByPrimaryKey(id);
        return Message.success(houseEntity);
    }

    @ApiOperation(value = "分页查询楼栋")
    @PostMapping("findPage")
    public Message findPageHouse(int pageNum, int pageSize, String houseName) {
        log.info(">>>>>>>>>>>>>>>通过单个id查询楼栋<<<<<<<<<<<<<<<<<<<<<");
        PageHelper.startPage(pageNum, pageSize);
        List<HouseEntity> listhouse = houseServer.findPageHouse(houseName);
        return Message.success(new PageInfo(listhouse));
    }


}
