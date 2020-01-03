package com.yscoco.robot.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yscoco.robot.Exception.BizException;
import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.common.result.Message;
import com.yscoco.robot.entity.HouseEntity;
import com.yscoco.robot.redis.redisUtil.RedisUtil;
import com.yscoco.robot.util.HttpClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Xiong
 * @Date: 2020/1/2 16:17
 */
@Slf4j
@RestController
@RequestMapping("fluorite")
@Api(tags = {"萤石开放平台"})
public class FluoriteController {

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "获取token")
    @GetMapping("getToken")
    public Message findPageHouse() {

        if (redisUtil.hasKey("fluorite")) {
            return Message.success(redisUtil.get("fluorite"));
        }
        log.info(">>>>>>>>>>>>>>>获取token<<<<<<<<<<<<<<<<<<<<<");
        String url = "https://open.ys7.com/api/lapp/token/get";
        Map<String, String> map = new HashMap<String, String>();
        map.put("appKey", "feadcafa8d8c4209adecf41920f6d9d0");
        map.put("appSecret", "78a715e7ec1d4221083df8d333b2e7ec");
        JSONObject res = HttpClientUtil.doPostWithFormDate(url, map);
        String code = res.getString("code");
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + code);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + res.toJSONString());
        if (!"200".equals(code)) {
            throw new BizException(Code.ERROR);
        }
        JSONObject jsonObject = res.getJSONObject("data");
        String token = jsonObject.getString("accessToken");
        redisUtil.set("fluorite", token, jsonObject.getLong("expireTime"));
        log.info(res.toJSONString());
        return Message.success(token);
    }

}
