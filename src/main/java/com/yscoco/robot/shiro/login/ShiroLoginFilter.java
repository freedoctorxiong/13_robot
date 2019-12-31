package com.yscoco.robot.shiro.login;

import com.alibaba.fastjson.JSON;


import com.yscoco.robot.common.result.Code;
import com.yscoco.robot.common.result.Message;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @Author xiong
 * @Date 2019/8/26 13:41
 **/

public class ShiroLoginFilter extends FormAuthenticationFilter {

    public ShiroLoginFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONSif (request instanceof HttpServletRequest) {
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("utf-8");
        res.setHeader("Content-type", "application/json;charset=UTF-8");
        res.setCharacterEncoding("utf-8");
        PrintWriter writer = res.getWriter();

        Message message = new Message(Code.NO_LOGIN_ERROR);
        writer.write(JSON.toJSONString(message));
        writer.close();
        return false;
    }

}
