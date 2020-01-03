package com.yscoco.robot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * 提交数据
 *
 * @author Administrator
 */
@Slf4j
public class HttpClientUtil {

    /**
     * 发送get请求
     *
     * @param url
     * @return str    //返回请求的内容
     * @throws Exception
     */
    public static String doGet(String url) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String returnValue = null;
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = httpClient.execute(httpGet);
            //判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                returnValue = EntityUtils.toString(response.getEntity(), "UTF-8");
                return returnValue;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    log.error("response关闭失败！");
                    e.printStackTrace();
                }
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("httpClient关闭失败！");
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    /**
     * 发送Post请求 带json参数
     *
     * @param url  要请求的地址
     * @param json 要发送的json格式数据
     * @return 返回的数据
     */
    public static String doPostWithJson(String url, JSONObject json) {
        String returnValue = null;
        CloseableHttpClient httpclient = null;
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //创建httpclient对象
            httpclient = HttpClients.createDefault();
            //创建http POST请求
            HttpPost httpPost = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(json.toJSONString(), "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            //发送请求，获取返回值
            returnValue = httpclient.execute(httpPost, responseHandler);
        } catch (Exception e) {
            log.error("发送post请求失败！" + e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("httpclient关闭失败！" + e.getMessage());
            }
        }
        return returnValue;
    }

    /**
     * 发送Post请求 带json参数
     *
     * @param url   要请求的地址
     * @param parms 要发送的Map格式数据
     * @return 返回的数据
     */
    public static JSONObject doPostWithFormDate(String url, Map<String, String> parms) {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> list = new ArrayList<>();
        parms.forEach((key, value) -> list.add(new BasicNameValuePair(key, value)));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            if (Objects.nonNull(parms) && parms.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            }
            InputStream content = httpPost.getEntity().getContent();
            InputStreamReader inputStreamReader = new InputStreamReader(content, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String readLine = bufferedReader.readLine();
            System.out.println("readLine===================================" + readLine);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(entity, "UTF-8"));
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(httpClient)) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
