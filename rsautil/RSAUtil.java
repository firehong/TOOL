package com.cgmcomm.utils.rsautil;

import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019/7/8 11:03
 * @Description RSA加解密工具
 */
public class RSAUtil {

    /**
     * @date 2019/7/8 11:03
     * @Description 生成密钥对
     * @Param
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * @date 2019/7/8 11:03
     * @Description 获取公钥(Base64编码)
     * @Param
     */
    public static String getPublicKey(KeyPair keyPair){
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * @date 2019/7/8 11:04
     * @Description 获取私钥(Base64编码)
     * @Param
     */
    public static String getPrivateKey(KeyPair keyPair){
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return byte2Base64(bytes);
    }

    /**
     * @date 2019/7/8 11:04
     * @Description 将Base64编码后的公钥转换成PublicKey对象
     * @Param
     */
    public static PublicKey string2PublicKey(String pubStr) throws Exception{
        byte[] keyBytes = base642Byte(pubStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * @date 2019/7/8 11:04
     * @Description 将Base64编码后的私钥转换成PrivateKey对象
     * @Param
     */
    public static PrivateKey string2PrivateKey(String priStr) throws Exception{
        byte[] keyBytes = base642Byte(priStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * @date 2019/7/8 11:04
     * @Description 公钥加密
     * @Param
     */
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * @date 2019/7/8 11:06
     * @Description 公钥解密
     * @Param
     */
    public static byte[] publicDecrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * @date 2019/7/8 11:07
     * @Description 私钥加密
     * @Param
     */
    public static byte[] privateEncrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * @date 2019/7/8 11:04
     * @Description 私钥解密
     * @Param
     */
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * @date 2019/7/8 11:05
     * @Description 字节数组转Base64编码
     * @Param
     */
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * @date 2019/7/8 11:05
     * @Description Base64编码转字节数组
     * @Param
     */
    public static byte[] base642Byte(String base64Key) throws IOException{
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    public static void main(String[] args) throws Exception{

        //数据
        Map map = new HashMap();
        map.put("1","中国挖随");
        String data = JSON.toJSONString(map);
        System.out.println("加密前字符串："+data);


        KeyPair pair = getKeyPair();
        //公钥
        String PublicKey = getPublicKey(pair);
        //私钥
        String PrivateKey = getPrivateKey(pair);
        //将Base64编码后的公钥转换成PublicKey对象
        PublicKey publicKey = string2PublicKey(PublicKey);
        //将Base64编码后的私钥转换成PrivateKey对象
        PrivateKey privateKey = string2PrivateKey(PrivateKey);

        /**************************公钥加密，私钥解密*****************************/
        //用公钥加密
        byte[] publicEncrypt = publicEncrypt(data.getBytes(), publicKey);
        //加密后的内容Base64编码
        String byte2Base64 = byte2Base64(publicEncrypt);
        System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);

        //加密后的内容Base64解码
        byte[] base642Byte = base642Byte(byte2Base64);
        //用私钥解密
        byte[] privateDecrypt = privateDecrypt(base642Byte, privateKey);
        //解密后的明文
        System.out.println("解密后的明文: " + new String(privateDecrypt));
        /**************************公钥加密，私钥解密*****************************/

        /**************************私钥加密，公钥解密*****************************/
        byte[] privateEncrypt = privateEncrypt(data.getBytes(),privateKey);
        //加密后的内容Base64编码
        String byte2Base642 = byte2Base64(privateEncrypt);
        System.out.println("私钥加密并Base64编码的结果：" + byte2Base642);

        //公钥解密
        byte[] publicDecrypt = publicDecrypt(base642Byte(byte2Base642),publicKey);
        //解密后的内容Base64编码
        System.out.println("公钥解密明文：" + new String(publicDecrypt));

        /**************************私钥加密，公钥解密*****************************/
    }
}
