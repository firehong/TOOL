package com.cgmcomm.utils;

import com.alibaba.fastjson.JSON;
import com.cgmcomm.cgmClient.utils.LogUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @auther Macro
 * @date 2019/6/17 10:30
 * @Description Http工具类
 */
public class HttpUtil {
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @return
     */
    public static String post(String url,String jsonParam,String contentType){
        if(contentType==null){
            contentType = "application/json;charset=utf-8";
        }
        //post请求返回结果
        @SuppressWarnings("resource")
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String reponseStr = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                method.setEntity(entity);
            }
            method.setHeader("Content-Type", contentType);
            HttpResponse result = httpClient.execute(method);
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    reponseStr = EntityUtils.toString(result.getEntity(),"utf-8");
                    return reponseStr;
                } catch (Exception e) {
                    LogUtils.getLogger().error("post请求提交失败:{},{}" , url, e);
                }
            }else{
                LogUtils.getLogger().error("post请求提交失败:{},{}" ,url, result);
            }
        } catch (IOException e) {
            LogUtils.getLogger().error("post请求提交失败:{},{}" ,url, e);
        }
        return reponseStr;
    }

    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static String get(String url,String contentType){
        if(contentType==null){
            contentType = "application/json;charset=utf-8";
        }
        String responseString = null;
        try {
            @SuppressWarnings("resource")
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);

            request.setHeader("Content-Type",contentType);
            HttpResponse response = httpClient.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                return EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
            } else {
                LogUtils.getLogger().error("get请求提交失败:{},失败响应{}" , url,response);
                responseString = JSON.toJSONString(response);
            }
        } catch (IOException e) {
            LogUtils.getLogger().error("get请求提交失败:{},{}" ,url, e);
        }
        return responseString;
    }

}
