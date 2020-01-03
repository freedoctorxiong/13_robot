package com.yscoco.robot.server.impl;


import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.dao.UserEntityMapper;
import com.yscoco.robot.entity.UserEntity;
import com.yscoco.robot.entity.aliyun.BindInfo;
import com.yscoco.robot.redis.redisUtil.RedisUtil;
import com.yscoco.robot.server.alisms.AliyunSmsSenderService;
import com.yscoco.robot.server.user.LoginServer;
import com.yscoco.robot.shiro.CustomToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * @Author: Xiong
 * @Date: 2019/10/16 14:17
 */
@Service
@Slf4j
public class LoginServerImpl implements LoginServer {

    @Autowired
    private UserEntityMapper userEntityMapper;

    private int counts = 10;
    private int totalTime = 300;


    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AliyunSmsSenderService aliyunSmsSenderService;

    String total = "total";


    @Override
    public Message register(UserEntity userEntity) {


        userEntity.setCreateTime(new Date());
        userEntity.setPassword(DigestUtils.md5DigestAsHex(userEntity.getPassword().getBytes()));
        int result = userEntityMapper.insertSelective(userEntity);
        if (result != 1) {
            throw new BizException(Code.OPERATION_FAILURE_ERROR);
        }
        return Message.success();
    }

    @Override
    public Message sendSms(String mobilPhone) {

        if (redisUtil.hasKey(total + mobilPhone)) {
            int count = (int) redisUtil.get(total + mobilPhone);
            if (count >= counts) {
                throw new BizException(Code.VCODE_LIMIT_ERROR);
            }
            redisUtil.incr(total + mobilPhone, 1);
        } else {
            redisUtil.set("total" + mobilPhone, 1, totalTime);
        }
        String Vcode = "666666";

        //String sms = RandomUtils.getNumCode();
        //调用阿里发送消息
       /* SendSmsResponse response = aliyunSmsSenderService.sendSms(mobilPhone, "{\"code\":\"" + sms + "\"}", "SMS_179710026");
        if (!"OK".equals(response.getCode())) {
            throw new BizException(Code.SMS_SEND_ERROR);
        }*/

        redisUtil.set("sms" + mobilPhone, Vcode, 300);

        return Message.success();
    }

    @Override
    public Message doLogin(CustomToken token) {
        try {
            log.info("进入登录");
            Subject subject = SecurityUtils.getSubject();
            log.info(token + "-----------dologin-------");
            subject.login(token);

            String authorization = (String) subject.getSession().getId();
            return Message.success(authorization);
        } catch (IncorrectCredentialsException e) {
            return new Message(Code.PASSWORD_ERROR);
        } catch (LockedAccountException e) {
            return new Message(Code.USER_DISABLED_ERROR);
        } catch (AuthenticationException e) {
            return new Message(Code.USER_NOT_EXIST_ERROR);
        }
    }

    /**
     * 校验验证码
     *
     * @param bindInfo
     * @return
     */
    @Override
    public boolean checkcode(BindInfo bindInfo) {

        String code = (String) redisUtil.get("sms" + bindInfo.getTellphone());

        log.info(bindInfo.getTellphone() + ">>>>>>>>>>>>phone<<<<<<<<<");
        log.info(code + "--------code-------");
        if (StringUtils.isBlank(code)) {
            throw new BizException(Code.VCODE_FAILURE);
        }
        if (code.equals(bindInfo.getCode())) {
            return true;
        } else {
            throw new BizException(Code.VCODE_ERROR);
        }

    }

}
