package com.cgmcomm.webapi.utils.weixinpay.config;

/**
 * @date 2020/4/25 15:19
 * @Description 微信支付相关配置信息
 * @Param
 */
public class WxPayConfig {
	
	/**微信账号信息配置**/
	
	/**公众号appId**/
	public static final String appID = "";
	/**公众号商户号**/
	public static final String MchId = "";
	/**公众号商户号秘钥**/
	public static final String MchKey = "";



	/***app应用appId***/
	public static final String APP_AppID = "";
	/***app应用appSecret***/
	public static final String APP_AppSecret = "";
	/***app应用商户号***/
	public static final String APP_MchId = "";
	/***app应用商户密钥***/
	public static final String APP_MchKey = "";

	/***小程序应用appId***/
	public static final String XIAO_AppID = "";
	/***小程序应用appSecret***/
	public static final String XIAO_AppSecret = "";
	/**小程序商户号**/
	public static final String XIAO_MchId = "";
	/**小程序商户号秘钥**/
	public static final String XIAO_MchKey = "";

	/**商户证书路径**/
	public static final String windows_KeyPath = "";   // windows环境
	public static final String linux_KeyPath = "";    // linux环境
	
	//微信回调URL
	/***支付完成后的异步通知地址***/
	public static final String NotifyUrl = "http:///callBack/callBackWeiXinPay";	
	/***支付完成后的同步返回地址***/
	public static final String ReturnUrl = "http:///callBack/callBackWeiXinPay";

}
