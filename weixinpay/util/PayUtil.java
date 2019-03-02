package com.mcfish.util.weixinpay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.mcfish.base.exception.MyException;
import com.mcfish.util.common.ResourceUtils;
import com.mcfish.util.weixinpay.config.BasicInfo;

/**
 * 微信支付相关工具类
 * 
 * @author wuhong  2017年11月27日 下午12:25:58 
 * @version share 1.0
 */
public class PayUtil {
       
	
	   //日志记录
	   protected static Logger log = Logger.getLogger(PayUtil.class);
	   //随机字符串范围
	   private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHI"
	   		                                   + "JKLMNOPQRSTUVWXYZ0123456789";
	   private static final java.util.Random RANDOM = new java.util.Random();
	   
	   //支付证书认证
	   private static SSLContext sslContext;
	   
	   
	   /**
	    * 发起微信支付相关请求
	    * @return
	    */
	   public static String ssl(String url,String data,String mchId){
	        StringBuffer message = new StringBuffer();
	        try {
	        	sslContext = initSSLContext(mchId);
	            SSLConnectionSocketFactory sslConnectionSocketFactory = 
	            		new SSLConnectionSocketFactory(
		                    sslContext,
		                    new String[]{"TLSv1"},
		                    null,
		                    new DefaultHostnameVerifier());
	            BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager(
	                    RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslConnectionSocketFactory)
                        .build(),
		                null,
		                null,
		                null
	            );
	            HttpClient httpClient = HttpClientBuilder.create()
	                    .setConnectionManager(connManager)
	                    .build();
	            
	            HttpPost httpost = new HttpPost(url);
	            httpost.addHeader("Content-Type", 
	            		    "application/x-www-form-urlencoded; charset=UTF-8");
	            httpost.addHeader("Host", "api.mch.weixin.qq.com");
	            httpost.addHeader("User-Agent", 
	            		   "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
	            httpost.setEntity(new StringEntity(data, "UTF-8"));
	            
	            HttpResponse response = httpClient.execute(httpost);
	            try {
	                HttpEntity entity = response.getEntity();
	                if (entity != null) {
	                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
	                    String text;
	                    while ((text = bufferedReader.readLine()) != null) {
	                        message.append(text);
	                    }
	                }
	                EntityUtils.consume(entity);
	            } catch (IOException e) {
	            	log.error("发起微信支付请求过程异常--》》"+e);
	                e.printStackTrace();
	            } finally {
	                
	            }
	        } catch (Exception e1) {
	        	log.error("发起微信支付请求异常--》》"+e1);
	            e1.printStackTrace();
	        } 
	        return message.toString();
	    }
	   
	   
	
	   /**
	    * 生成随机字符串
	    * @return
	    */
	   public static String getRandomStr() {
	     StringBuilder sb = new StringBuilder();
	     for (int i = 0; i < 16; i++) {
	       sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
	     }
	     return sb.toString();
	   }
	   
	   

	   /**
	    * 初始化微信证书
	    * @param req
	    * @return
	    * @throws MyException 
	    */
	   public static SSLContext initSSLContext(String mchId) 
			   throws MyException {
	       FileInputStream inputStream = null;
	       try {
	    	   File file = new File((ResourceUtils.getFile(BasicInfo.KeyPath)));
	           inputStream = new FileInputStream(file);
	       } catch (IOException e) {
	    	   log.error("商户证书不正确-->>"+e);
	           throw new MyException("证书不正确！", e);
	       }
	       try {
	           KeyStore keystore = KeyStore.getInstance("PKCS12");
	           char[] partnerId2charArray = mchId.toCharArray();
	           keystore.load(inputStream, partnerId2charArray);
	           
	           // 实例化密钥库 & 初始化密钥工厂
	           KeyManagerFactory kmf = KeyManagerFactory.getInstance(
	            			KeyManagerFactory.getDefaultAlgorithm());
	           kmf.init(keystore,partnerId2charArray);
	           // 创建 SSLContext
	           SSLContext sslContext = SSLContext.getInstance("TLS");
	           sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
	           return sslContext;
	       } catch (Exception e) {
	    	   log.error("商户秘钥不正确-->>"+e);
	           throw new MyException("秘钥不正确！", e);
	       } finally {
	          
	       }
	   }
	   
	   
}
