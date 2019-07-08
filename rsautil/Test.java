package com.cgmcomm.utils.rsautil;

import com.alibaba.fastjson.JSON;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019/7/8 11:36
 * @Description 加解密过程测试
 */
public class Test {
    /**
     * @date 2019/7/8 11:24
     * @Description 项目中实际使用 ＲＳＡ交换密码，　用密码做ＡＥＳ对称加密。
     *            具体过程：
     *              1、客户端每次请求前，都随机生成不同的AES密钥，保存到变量aesKey中
     *              2、使用aesKey对要传输的信息进加密，得到加密内容A
     *              3、通过预置在客户端的RSA公钥rsaPublicKey对aesKey加密，得到加密内容B
     *              4、将内容A和内容B传输到服务端
     *              5、服务端接收到内容A和内容B
     *              6、使用预置在服务器端的RSA私钥rsaPrivateKey对内容B进行解密，还原得到客户端的aesKey
     *              7、使用刚刚得到的aesKey对内容A进行解密，得到实际要传输的内容
     *
     *              服务端往客户端响应加密数据
     *                  1、以上第6步中，服务器得到了客户端的aesKey，用这个aesKey对要响应的内容进行加密，得到内容C，将内容C传输给客户端。
     *                  2、客户端接收到内容C，使用本地变量aesKey对内容C解密，得到实际的响应内容。
     * @Param
     */
    public static void main(String[] args) throws Exception{
        //数据
        Map map = new HashMap();
        map.put("1","中国挖随");
        String data = JSON.toJSONString(map);
        System.out.println("加密前字符串："+data);

        //获取aes秘钥
        String key = AESUtil.genKeyAES();
        //获取rsa公钥和私钥
        KeyPair pair = RSAUtil.getKeyPair();
        //公钥
        String PublicKey = RSAUtil.getPublicKey(pair);
        //私钥
        String PrivateKey = RSAUtil.getPrivateKey(pair);

        /**********客户端加密************/
        //aes秘钥加密
        byte[] bytes = RSAUtil.publicEncrypt(RSAUtil.base642Byte(key),RSAUtil.string2PublicKey(PublicKey));
        String mkey = RSAUtil.byte2Base64(bytes);
        System.out.println("加密的aes字符串："+mkey);

        //加密返回数据
        byte[] ret = AESUtil.encryptAES(data.getBytes(),AESUtil.loadKeyAES(key));
        String rdata = AESUtil.byte2Base64(ret);
        System.out.println("加密的返回数据字符串："+rdata);
        /***********客户端加密***********/

        /***********服务端解密***********/

        //获取aes秘钥key
        byte[] kbkey = RSAUtil.privateDecrypt(RSAUtil.base642Byte(mkey),RSAUtil.string2PrivateKey(PrivateKey));
        String bkey = RSAUtil.byte2Base64(kbkey);
        System.out.println("客户端解密的aes秘钥字符串："+bkey);
        //aes解密
        byte[] jbret = AESUtil.decryptAES(AESUtil.base642Byte(rdata),AESUtil.loadKeyAES(bkey));
        System.out.println("客户端解密的data字符串："+new String(jbret));

        /***********服务端解密***********/
    }
}
