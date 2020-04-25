package com.cgmcomm.webapi.utils.weixinpay.config;

/**
 * @date 2020/4/25 15:19
 * @Description 微信支付相关配置信息
 * @Param
 */
public class WxPayConfig {
	
	/**微信账号信息配置**/
	
	/**公众号appId**/
	public static final String appID = "wx515341ebf789cbdc";
	/**公众号appSecret**/
	public static final String appSecret = "";
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
	public static final String KeyPath = "";
	public static final String linux_KeyPath = "";
	
	//微信回调URL
	/***支付完成后的异步通知地址***/
	public static final String NotifyUrl = "http:///callBack/callBackWeiXinPay";	
	/***支付完成后的同步返回地址***/
	public static final String ReturnUrl = "http:///callBack/callBackWeiXinPay";
	
	
	//微信统一下单接口
	public static final String unifiedordersurl ="https://api.mch.weixin.qq.com/pay/unifiedorder";		
	//微信红包退款接口
	public static final String httpurl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	//微信退款接口
	public static final String refundurl ="https://api.mch.weixin.qq.com/secapi/pay/refund";
	//企业转账接口
	public static final String transfersurl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
}
