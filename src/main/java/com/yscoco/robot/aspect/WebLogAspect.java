package com.yscoco.robot.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author xiong
 * @Date 2019/8/26 13:41
 **/

@Aspect
@Component
public class WebLogAspect {
	private Logger logger =  LogManager.getLogger(WebLogAspect.class);
	
	@Pointcut("execution(public * com.yscoco.robot.controller..*.*(..))")
	public void webLog() {
	}
	
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 记录下请求内容
		logger.info("************************Request start*******************************");
		logger.info("Execution thread:" + Thread.currentThread().getName());
		logger.info("Request address: " + request.getRequestURL().toString());
		
		logger.info("Request method: " + request.getMethod());
		
		logger.info("IP: " + request.getRemoteAddr());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			logger.info("parameter:{},value:{}", name, request.getParameter(name));
		}
	}
	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		if(ret.toString().length()<100){
			logger.info("Response results: " + ret);
		}
		logger.info("************************End of request*******************************");
		
	}
}
