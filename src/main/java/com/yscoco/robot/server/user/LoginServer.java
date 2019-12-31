package com.yscoco.robot.server.user;


import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.aliyun.BindInfo;
import com.yscoco.robot.shiro.CustomToken;

/**
 * @Author:   Xiong
 * @Date:     2019/10/11 17:44
 */
public interface LoginServer {

    /**
     * 发送验证码
     * @param MobilPhone
     * @return
     */
    Message sendSms(String MobilPhone) throws BizException;

    /**
     *  校验验证码
     * @param bindInfo
     * @return
     */
    boolean checkcode(BindInfo bindInfo) throws BizException;

    /**
     * 生成token登陆
     * @param token
     * @return
     */
    Message doLogin(CustomToken token);
    
}
