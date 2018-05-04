package com.share.util.weixinpay.config;
/**
 * 基本配置信息类
 * @author TangLin
 * @date 2017年9月6日 下午7:06:42 
 * @version 1.0
 */

public class BasicInfo {
	
	/**微信账号信息配置**/
	
	//公众号appId
	public static final String appID = "wx01e2df0fb202ae3b";
	//公众号appSecret
	public static final String AppSecret = "4d2739105e6c09b35ed52ebde19f5a57";
	//商户证书路径
	public static final String KeyPath = "/apiclient_cert.p12";
	
	//微信统一下单接口
	public static final String unifiedordersurl ="https://api.mch.weixin.qq.com/pay/unifiedorder";		
	//微信红包退款接口
	public static final String httpurl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	//微信退款接口
	public static final String refundurl ="https://api.mch.weixin.qq.com/secapi/pay/refund";
	//企业转账接口
	public static final String transfersurl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
	//公众号商户号
	public static final String MchId = "1485864742";
	//公众号商户号秘钥
	public static final String MchKey = "d2bd5a310dec8969802d4cf8c4578d20";	
	//商家名
	public static final String Send_Name = "佳电";
	
	
	/***app应用appId***/
	public static final String APP_AppID = "wx5c8c5dc33d7ddc91";
	/***app应用appSecret***/
	public static final String APP_AppSecret = "921416eeadf854571aa18c3da8fdb18e";
	/***app应用商户号***/
	public static final String APP_MchId = "1488903292";
	/***app应用商户密钥***/
	public static final String APP_MchKey = "dsad6f7shc65s9HJ7Y6shcnhaluyxntw";
	
	/***小程序应用appId***/
	public static final String XIAO_AppID = "wxa68e3f31ba28d2c5";
	/***小程序应用appSecret***/
	public static final String XIAO_AppSecret = "087e1cd572a06052d39c9838b6f7c5c7";
	
	//微信回调URL
	/***支付完成后的异步通知地址***/
	public static final String NotifyUrl = "http://39.108.83.186/api/callBackController/callBackWeiXinPay";	
	/***支付完成后的同步返回地址***/
	public static final String ReturnUrl = "http://39.108.83.186/api/callBackController/callBackWeiXinPay";
	
}
