package com.cgmcomm.utils.rsautil;

import com.alibaba.fastjson.JSON;
import com.cgmcomm.utils.rsautil.utils.Base64Utils;
import com.cgmcomm.utils.rsautil.utils.RSAUtils;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密解密
 * @author macro  2018年1月8日下午2:38:14
 * MCAdmin 0.0.1
 */
public class RSA{
	 //日志记录
	 private static Logger log = Logger.getLogger(RSA.class);
    
    /**
     * 私钥加密
     * @param data
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String getEncryptByPrivateKey(String data,String privateKey) throws Exception {
    	  byte[] bt = data.getBytes();
          byte[] encodedData = RSAUtils.encryptByPrivateKey(bt, privateKey);
          return Base64Utils.encode(encodedData);
    }
    
    /**
     * 公钥加密
     * @param data
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    public static String getEncryptByPublicKey(String data,String publicKey) throws Exception {
  	    byte[] bt = data.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(bt, publicKey);
        return Base64Utils.encode(encodedData);
    }
    
    /**
     * 公钥解密
     * @param data
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    public static String decipheringByPublicKey(String data,String publicKey) throws Exception {
    	 byte[] decodedData = RSAUtils.decryptByPublicKey(Base64Utils.decode(data), publicKey);
         String target = new String(decodedData);
         return target;
    }
    
    /**
     * 私钥解密
     * @param data
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String decipheringByPrivateKey(String data,String privateKey) throws Exception {
   	    byte[] decodedData = RSAUtils.decryptByPrivateKey(Base64Utils.decode(data), privateKey);
        String target = new String(decodedData);
        return target;
    }
   
   /**
    * 验证签名
    * @param data 加密后的字符串
    * @param privateKey 私钥
    * @param publicKey 公钥
    * @return
    * @throws Exception
    */
   public static boolean verify(String data,String privateKey,String publicKey) throws Exception {
	   byte[] decodedData = Base64Utils.decode(data);
	   String sign = RSAUtils.sign(decodedData, privateKey);
       return RSAUtils.verify(decodedData, publicKey, sign);
   }

    public static void main(String[] args) throws Exception {
        String j = test();
    }

    public static String test() throws Exception {
       //获取公私钥对
        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        String publicKey = RSAUtils.getPublicKey(keyMap);
        String privateKey = RSAUtils.getPrivateKey(keyMap);

        //数据
        Map map = new HashMap();
        map.put("1","中国挖随");
        String tse = JSON.toJSONString(map);

        System.out.println("加密前字符串："+tse);

        String jai = getEncryptByPrivateKey(tse,privateKey);
        System.out.println("加密后字符串："+new String(jai));

        String ret = decipheringByPublicKey(jai,publicKey);
        System.out.println("解密后的字符串=="+ret);

        return ret;
    }
  
}
