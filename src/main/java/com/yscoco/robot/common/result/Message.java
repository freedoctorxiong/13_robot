package com.yscoco.robot.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @author lanston
 * 使用者请注意 msgCode的get 方法改造 反馈string
 *
 */
@JsonInclude(Include.NON_NULL)
@ApiModel
public class Message<T> implements Serializable{

	private static final long serialVersionUID = -3649275882164060087L;
	public static <T> Message<T> success(T data){
		Message<T> msg = new Message<T>(Code.SUCCESS,data);
		return msg;
	}
	public static <T> Message<T> success(){
		Message<T> msg = new Message<T>(Code.SUCCESS);
		return msg;
	}
	@ApiModelProperty(value = "反馈码,0-成功，其他失败")
	private String code;
	@ApiModelProperty(value = "反馈信息")
	private String msg;
	@ApiModelProperty(value = "反馈数据,格式不限")
	private T data;
	
	public Message() {
		super();
	}
	public Message(Code msgCode) {
		super();
		this.setCode(msgCode.getCode());
		this.setMsg(msgCode.getMsg());
	}

	public Message(Code msgCode, T data) {
		super();
		this.setCode(msgCode.getCode());
		this.setMsg(msgCode.getMsg());
		this.setData(data);
	}
	public Message(String code, String msg, T data) {
		super();
		this.setCode(code);
		this.setMsg(msg);
		this.setData(data);
	}
	public Message(String code, String msg) {
		super();
		this.setCode(code);
		this.setMsg(msg);
	}
	
	public String getCode() {
		return code.toString();
	}

	public void setCode(String msgCode) {
		this.code = msgCode;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
