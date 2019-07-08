package com.cgmcomm.utils.rsautil;
import com.alibaba.fastjson.JSON;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019/7/8 11:18
 * @Description AES对称加密
 */
public class AESUtil {

    /**
     * @date 2019/7/8 11:23
     * @Description 生成AES秘钥，然后Base64编码
     * @Param
     */
    public static String genKeyAES() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        String base64Str = byte2Base64(key.getEncoded());
        return base64Str;
    }

    /**
     * @date 2019/7/8 11:23
     * @Description 将Base64编码后的AES秘钥转换成SecretKey对象
     * @Param
     */
    public static SecretKey loadKeyAES(String base64Key) throws Exception{
        byte[] bytes = base642Byte(base64Key);
        SecretKeySpec key = new SecretKeySpec(bytes, "AES");
        return key;
    }

    /**
     * @date 2019/7/8 11:23
     * @Description 字节数组转Base64编码
     * @Param
     */
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * @date 2019/7/8 11:24
     * @Description Base64编码转字节数组
     * @Param
     */
    public static byte[] base642Byte(String base64Key) throws IOException{
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    /**
     * @date 2019/7/8 11:24
     * @Description 加密
     * @Param
     */
    public static byte[] encryptAES(byte[] source, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(source);
    }

    /**
     * @date 2019/7/8 11:24
     * @Description 解密
     * @Param
     */
    public static byte[] decryptAES(byte[] source, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(source);
    }

    public static void main(String[] args) throws Exception{

        //数据
        Map map = new HashMap();
        map.put("1","中国挖随");
        String data = JSON.toJSONString(map);
        System.out.println("加密前字符串："+data);

        //生成AES秘钥，并Base64编码
        String base64Str = genKeyAES();
        //将Base64编码的字符串，转换成AES秘钥
        SecretKey aesKey = AESUtil.loadKeyAES(base64Str);
        //加密
        byte[] encryptAES = AESUtil.encryptAES(data.getBytes(), aesKey);
        //加密后的内容Base64编码
        String byte2Base64 = AESUtil.byte2Base64(encryptAES);
        System.out.println("加密并Base64编码的结果：" + byte2Base64);

        //===================服务端================
        //将Base64编码的字符串，转换成AES秘钥
        SecretKey aesKey2 = AESUtil.loadKeyAES(base64Str);
        //加密后的内容Base64解码
        byte[] base642Byte = AESUtil.base642Byte(byte2Base64);
        //解密
        byte[] decryptAES = AESUtil.decryptAES(base642Byte, aesKey2);
        //解密后的明文
        System.out.println("解密后的明文: " + new String(decryptAES));
    }
}
