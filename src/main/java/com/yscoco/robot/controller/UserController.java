package com.yscoco.robot.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.HouseEntity;
import com.yscoco.robot.entity.UserEntity;
import com.yscoco.robot.server.user.UserServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2019/12/5 14:35
 */
@Slf4j
@RestController
@RequestMapping("user")
@Api(tags = {"用户相关接口"})
public class UserController {

    @Autowired
    private UserServer userServer;

    @ApiOperation(value = "删除用户")
    @PostMapping("deleteUser")
    public Message deleteByPrimaryKey(@RequestParam Long id) {
        userServer.deleteByPrimaryKey(id);
        return Message.success();
    }

    @ApiOperation(value = "增加用户")
    @PostMapping("insertUser")
    public Message insert(@RequestBody UserEntity userEntity) {
        log.info(">>>>>>>>>>>>>>>增加用户<<<<<<<<<<<<<<<<<<<<<");
        userServer.insertUser(userEntity);
        return Message.success();
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("updateUser")
    public Message updateByPrimaryKeySelective(@RequestBody UserEntity userEntity) {
        log.info(">>>>>>>>>>>>>>>修改用户<<<<<<<<<<<<<<<<<<<<<");
        userServer.updateByPrimaryKeySelective(userEntity);
        return Message.success();
    }

    @ApiOperation(value = "单个id查询用户")
    @PostMapping("selectUser")
    public Message selectByPrimaryKey(@RequestParam Long id) {
        log.info(">>>>>>>>>>>>>>>通过单个id查询用户<<<<<<<<<<<<<<<<<<<<<");
        UserEntity userEntity = userServer.selectByPrimaryKey(id);
        return Message.success(userEntity);
    }

    @ApiOperation(value = "分页查询用户")
    @PostMapping("findPage")
    public Message findPageHouse(int pageNum, int pageSize, String userName) {
        log.info(">>>>>>>>>>>>>>>分页查询用户<<<<<<<<<<<<<<<<<<<<<");
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> listuser = userServer.findPageUsers(userName);
        return Message.success(new PageInfo(listuser));
    }

}
