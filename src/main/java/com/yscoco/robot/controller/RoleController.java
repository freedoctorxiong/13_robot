package com.yscoco.robot.controller;

import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.RoleEntity;
import com.yscoco.robot.entity.UserEntity;
import com.yscoco.robot.server.user.RoleServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Xiong
 * @Date: 2020/1/3 14:52
 */
@Slf4j
@RestController
@RequestMapping("login")
@Api(tags = {"角色管理"})
public class RoleController {

    @Autowired
    private RoleServer roleServer;


    @ApiOperation(value = "根据用户Id获得角色列表")
    @PostMapping("insertUser")
    public Message insert(Long userid) {
        log.info(">>>>>>>>>>>>>>>根据用户Id获得角色列表<<<<<<<<<<<<<<<<<<<<<");
        List<RoleEntity> listRoles = roleServer.findByUserId(userid);
        return Message.success(listRoles);
    }

}
