package com.mcfish.util.weixinpay.config;
/**
 * 基本配置信息类
 * @author TangLin
 * @date 2017年9月6日 下午7:06:42 
 * @version 1.0
 */

public class BasicInfo {
	
	/**微信账号信息配置**/
	
	/**公众号appId**/
	public static final String appID = "";
	/**公众号appSecret**/
	public static final String AppSecret = "";
	/**公众号商户号**/
	public static final String MchId = "";
	/**公众号商户号秘钥**/
	public static final String MchKey = "";	
	
	
	/***app应用appId***/
	public static final String APP_AppID = "wx03a8d12651b2548f";
	/***app应用appSecret***/
	public static final String APP_AppSecret = "ca6d99b82183939d17ea70829d8f9ff5";
	/***app应用商户号***/
	public static final String APP_MchId = "1526988291";
	/***app应用商户密钥***/
	public static final String APP_MchKey = "040C7864C649B42624FD0C08EBAC239D";
	
	/***小程序应用appId***/
	public static final String XIAO_AppID = "wxaf7d2e3816862521";
	/***小程序应用appSecret***/
	public static final String XIAO_AppSecret = "";
	/**小程序商户号**/
	public static final String XIAO_MchId = "1526988291";
	/**小程序商户号秘钥**/
	public static final String XIAO_MchKey = "040C7864C649B42624FD0C08EBAC239D";	
	

	/**商家名**/
	public static final String Send_Name = "海诺教育";
	/**商户证书路径**/
	public static final String KeyPath = "classpath:apiclient_cert.p12";
	
	//微信回调URL
	/***支付完成后的异步通知地址***/
	public static final String NotifyUrl = "http://106.14.115.107/callBack/callBackWeiXinPay";	
	/***支付完成后的同步返回地址***/
	public static final String ReturnUrl = "http://106.14.115.107/callBack/callBackWeiXinPay";
	
	
	//微信统一下单接口
	public static final String unifiedordersurl ="https://api.mch.weixin.qq.com/pay/unifiedorder";		
	//微信红包退款接口
	public static final String httpurl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	//微信退款接口
	public static final String refundurl ="https://api.mch.weixin.qq.com/secapi/pay/refund";
	//企业转账接口
	public static final String transfersurl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
}
