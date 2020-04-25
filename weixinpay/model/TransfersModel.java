package com.cgmcomm.webapi.utils.weixinpay.model;


import lombok.Data;

/**
 * 企业转账请求实体
 * 
 * @author wuhong  2017年11月8日 下午2:58:38 
 * @version 源佳 1.0
 */
@Data
public class TransfersModel {
	
	/***企业号corpid即为此appId****/
	private String mch_appid;
	/***商户号****/
	private String mchid;
	/***随机字符串****/
	private String nonce_str;
	/***签名****/
	private String sign;
	/***商户订单号****/
	private String partner_trade_no;
	/***用户openid****/	
	private String openid;
	/****校验用户姓名选项 
	 *  NO_CHECK：不校验真实姓名
        FORCE_CHECK：强校验真实姓名***/
	private String check_name;
	/***收款用户姓名****/
	private String re_user_name;
	/****金额(分)***/
	private Integer amount;
	/***企业付款描述信息****/
	private String desc;
	/***Ip地址****/
	private String spbill_create_ip;
}
