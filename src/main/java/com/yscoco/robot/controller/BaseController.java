package com.yscoco.robot.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.HouseEntity;
import com.yscoco.robot.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * @Author dscom
 * @Date 2019/8/12 10:46
 **/
@RestController
@RequestMapping("userinfo")
@Api(tags = {"查询当前用户的信息"})
public class BaseController {
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    @PostMapping("info")
    @ApiOperation(value = "查询当前用户的信息", notes = "//前端ajax的headers中必须传入Authorization的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "sessionid", required = true, dataType = "String", paramType = "query")
    })
    public Message getUserByHeader(ServletRequest request) throws Exception {
        //前端ajax的headers中必须传入Authorization的值
        String id = WebUtils.toHttp(request).getHeader("Authorization");
        Session session = redisSessionDAO.readSession(id);
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
        String userStr = JSON.toJSON(coll.getPrimaryPrincipal()).toString();
        UserEntity user = JSON.parseObject(userStr, UserEntity.class);
        return Message.success(user);
    }


}
