package com.yscoco.robot.controller;


import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.RequestLimit;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.UserEntity;
import com.yscoco.robot.entity.aliyun.BindInfo;
import com.yscoco.robot.entity.pojo.RoleType;
import com.yscoco.robot.server.user.LoginServer;
import com.yscoco.robot.server.user.RoleServer;
import com.yscoco.robot.server.user.UserServer;
import com.yscoco.robot.shiro.CustomToken;
import com.yscoco.robot.util.AESUtils;
import com.yscoco.robot.util.RegularCheckUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Xiong
 * @Date: 2019/10/16 14:14
 */
@Slf4j
@RestController
@RequestMapping("login")
@Api(tags = {"登录相关接口"})
public class LoginController {

    @Autowired
    private LoginServer loginServer;

    @Autowired
    private UserServer userServer;

    @Autowired
    private RoleServer roleServer;


    /**
     * 发送短信
     *
     * @param mobilPhone
     * @return
     */

    @RequestLimit(count = 20, time = 30000)
    @ApiOperation(value = "发送短信", notes = "发送短信")
    @PostMapping("sendSms")
    public Message sendSms(String mobilPhone,
                           @ApiParam(required = false, value = "加密请求数据") String reqData) {
        if (StringUtils.isNotEmpty(reqData)) {
            mobilPhone = AESUtils.getResultByKey("mobilPhone", AESUtils.getResult(reqData), String.class);
        }
        boolean isPhone = RegularCheckUtil.isMobile(mobilPhone);
        if (!isPhone) {
            throw new BizException(Code.MOBILE_ERROR);
        }
        return loginServer.sendSms(mobilPhone);
    }

    /**
     * 短信注册用户
     *
     * @param userEntity
     * @param roleType   身份
     * @param vcode      验证码
     * @return
     *//*
    @ApiOperation(value = "注册", notes = "短信注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vcode", value = "验证码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "roleType", value = "身份", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("sms/register")
    public Message register(@RequestBody UserEntity userEntity, @RequestParam RoleType roleType, @RequestParam String vcode) {
        return loginServer.register(userEntity, roleType, vcode);
    }*/


    /**
     * 手机号短信登录
     *
     * @param bindInfo
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "手机号短信登录", notes = "手机号短信登录")
    @RequestMapping(value = "/phonelogin", method = RequestMethod.POST)
    public Message phonelogin(@RequestBody BindInfo bindInfo) {

        if (StringUtils.isBlank(bindInfo.getTellphone()) || StringUtils.isBlank(bindInfo.getCode())) {
            throw new BizException(Code.NOT_PARAM);
        }
        boolean isPhone = RegularCheckUtil.isMobile(bindInfo.getTellphone());
        if (!isPhone) {
            throw new BizException(Code.MOBILE_ERROR);
        }

        loginServer.checkcode(bindInfo);

        CustomToken token = new CustomToken(bindInfo.getTellphone());
        return loginServer.doLogin(token);
    }


    /**
     * 账号密码登录
     *
     * @param userEntity
     * @return
     */
    @ApiOperation(value = "账号密码登录", notes = "账号密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Message doLogin(@RequestBody UserEntity userEntity) {
        if (StringUtils.isBlank(userEntity.getUsername()) || StringUtils.isBlank(userEntity.getPassword())) {
            throw new BizException(Code.NOT_PARAM);
        }
        CustomToken token = new CustomToken(userEntity.getUsername(), userEntity.getPassword());
        return loginServer.doLogin(token);
    }


    /**
     * 注销
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/logout")
    @ResponseBody
    @ApiOperation(value = "注销", notes = "登出注销")
    public Message logout(HttpServletRequest request) throws Exception {
        log.info("----------logout---------");
        Subject currentUser = SecurityUtils.getSubject();
        log.info("----------currentUser---------");
        currentUser.logout();
        return Message.success("注销成功");
    }

    /**
     * 未登录
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unauth", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Message unauth(HttpServletRequest request) throws Exception {
        return new Message(Code.NO_LOGIN_ERROR);
    }

    // @RequestMapping(value = "/403" ,method = RequestMethod.POST)
    @PostMapping("403")
    public Message error(HttpServletRequest request) throws Exception {
        return new Message(Code.SERVER_ERROR_MSG);
    }


}
