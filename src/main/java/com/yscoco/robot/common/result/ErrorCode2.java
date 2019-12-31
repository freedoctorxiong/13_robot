package com.yscoco.robot.common.result;

public class ErrorCode2 {

	/**
	 * 未知错误2000
	 */
	public static Integer serviceError = 2000;
	public static String serviceErrorMsg = "未知错误";
	public static String printeringUnknownErrorMsg = "未知错误:打印时发生未知错误";
	public static String operationFailed = "操作失败";

	/**
	 * 参数错误2001
	 */
	public static Integer parameterError = 2001;
	public static String nullErrorMsg = "请求参数错误:缺少请求参数";

	/**
	 * 请求微服务异常2004
	 */
	public static Integer requesTmicroServiceError = 2004;
	public static String requestTcpServiceErrorMsg = "请求微服务异常:请求tcp服务错误";

	/**
	 * 操作异常 2005
	 */
	public static Integer operationError = 2005;

	public static String operationFailureErrorMsg = "操作异常：数据库操作失败";

	/**
	 * 用户异常
	 */

	public static Integer userError = 2006;
	public static String passwordError = "用户异常：密码错误";
	public static String userDisabledError = "用户异常：该用户已被禁用";
	public static String userNotExistError = "用户异常：该用户不存在";
	public static String NoLoginError = "用户异常：未登录";
	public static String CodeError = "用户异常：验证码错误";
	public static String AuthorizationError = "用户异常：授权登陆失败";
	public static String AuthorizationstatError = "用户异常：授权状态出错";
	public static String AccessTokenError = "用户异常：授权状态出错";
	public static String MobileError = "用户异常：手机号不合法";
	public static String Nohaspower = "用户异常：用户没有此权限";


	public static Integer PheoneException = 2007;
	public static String BingTellPheone = "请绑定手机号";
	public static String HasTellPheone = "该手机号已经绑定用户";
	public static String NoHasTellPheone = "该手机号未绑定用户";

	public static Integer jurisdiction = 2008;
	public static String NullJurisdictionError = "系统无此权限，请联系管理员";

	public static Integer SmsError = 2009;
	public static String VcodeFailure = "验证码失效";
	public static String VcodeError = "验证码错误";
	public static String SmsSendError = "短信验证码发送失败";


	public static Integer EquipmentError = 2010;
	public static String NoBindEquipment = "用户没有绑定此设备";
	public static String hasBindEquipment = "您已添加过该设备哦";
	public static String NoAuthEquipment = "此用户没有授权该设备";

	public static Integer FileError = 2011;
	public static String NoSelectFile = "上传失败，请选择文件";



}
