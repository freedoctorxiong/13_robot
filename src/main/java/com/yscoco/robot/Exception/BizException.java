package com.yscoco.robot.Exception;


import com.yscoco.robot.common.result.Code;

/**
 * @author lanston
 * @CommonConstant 
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -4109645576970317430L;
	
	public String msg;
	private Code code;
	public BizException() {
	}
	
	public BizException(Code code) {
		this.msg = code.getMsg();
		this.code = code;
	}
	
	public BizException(String msg, Code code) {
		super();
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
	
	
}
