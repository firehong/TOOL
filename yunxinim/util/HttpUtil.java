package com.cgmcomm.utils.yunxinim.util;

import com.cgmcomm.cgmClient.config.ImConfig;
import com.cgmcomm.utils.yunxinim.model.RequestModel;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * @auther Macro
 * @date 2019/6/19 13:35
 * @Description 网易IM http请求工具类
 */
public class HttpUtil {

    //随机字符串范围
    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHI"
            + "JKLMNOPQRSTUVWXYZ0123456789";
    private static final java.util.Random RANDOM = new java.util.Random();

    /**
     * @date 2019/6/19 13:38
     * @Description 发起POST请求
     * @Param url 请求地址
     * @param map 请求参数
     */
    public static String post(String url, Map<String,String> map) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        //请求头参数设置
        String appKey = ImConfig.INSTANCE.getApiKey();
        String appSecret = ImConfig.INSTANCE.getSecretKey();
        String nonce =  getRandomStr(6);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        if (null != map) {
            // 设置请求的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            map.forEach((k, v) -> nvps.add(new BasicNameValuePair(k, v)));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        }
        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        // 打印执行结果
        return  EntityUtils.toString(response.getEntity(), "utf-8");
    }

    /**
     * 生成随机字符串
     * @return
     */
    public static String getRandomStr(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
        }
        return sb.toString();
    }

}
