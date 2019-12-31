package com.yscoco.robot.util;/*
package com.yscoco.tsleep.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SMSUtil {
    // 产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";



    @Value(value = "${sms_access_key_id}")
    private static String accessKeyId;
    @Value(value = "${sms_access_key_secret}")
    private static String accessKeySecret;
    @Value(value = "${sms_template_code}")
    private static String templateCode;
    @Value(value = "${sms_sign_name}")
    private static String signName;


    */
/**
     * @param phoneNumbers
     * @param content
     * @return
     *//*

    public static boolean sendSms(String phoneNumbers, String content) {

        log.info("accessKeyId>>>>>>>>>>>>>>>>>" + accessKeyId);
        log.info("accessKeySecret>>>>>>>>>>>>>>>>>" + accessKeySecret);
        log.info("templateCode>>>>>>>>>>>>>>>>>" + templateCode);
        log.info("signName>>>>>>>>>>>>>>>>>" + signName);

        boolean flag = false;
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + content + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            // System.out.println(response.getData());
            log.info(response.getData() + ">>>>>>>>>>>date");

          */
/*  JSONObject jsonObject = response.getData();
            if ("OK".equals(response.getData())) {
                String sss = response.getMessage();
                System.out.println(sss);
                flag = true;
            } else {
                log.error("短信发送失败: response={}", JSON.toJSONString(response));
            }
            // 此处可能会抛出异常，注意catch
            acsClient.getAcsResponse(request);
            flag = true;*//*



        } catch (Exception e) {
            log.error("短信发送失败: e={}", e.toString());
        } finally {
        }
        return flag;
    }

    */
/*
     *//*
*/
/*
    public static boolean sendSms(String phoneNumbers, String content) {
        boolean flag = false;
        try {
            // 可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            // 初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            // 组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();

            // 必填:待发送手机号
            request.setPhoneNumbers(phoneNumbers);
            // 必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            // 必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam("{\"code\":\"" + content + "\"}");
            // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            // request.setSmsUpExtendCode("90997");
            // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            request.setOutId("yourOutId");
            SendSmsResponse response = acsClient.getAcsResponse(request);
            if ("OK".equals(response.getCode())) {
                String sss = response.getMessage();
                System.out.println(sss);
                flag = true;
            } else {
                log.error("短信发送失败: response={}", JSON.toJSONString(response));
            }
            // 此处可能会抛出异常，注意catch
            acsClient.getAcsResponse(request);
            flag = true;
        } catch (Exception e) {
            log.error("短信发送失败: e={}", e.toString());
        } finally {
        }
        return flag;
    }*//*


    public static void main(String[] args) throws Exception {
        boolean a = sendSms("15549464350", "123456");
        System.out.println(a);
    }
}*/
