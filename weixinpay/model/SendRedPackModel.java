package com.cgmcomm.webapi.utils.weixinpay.model;


import lombok.Data;

/**
 * 微信公众号红包退款实体
 * @author TangLin
 * @date 2017年9月11日 下午5:00:53 
 * @version 1.0
 */
@Data
public class SendRedPackModel {
	
	private String nonce_str;// 随机字符串
    private String sign;// 签名
    private String mch_billno;// 商户订单号
    private String mch_id;// 商户号
    private String wxappid;// 公众账号
    private String send_name;// 商户名称
    private String re_openid;// 用户
    private int total_amount;// 付款金额 单位：分
    private int total_num;// 红包发放总人数
    private String wishing;// 红包祝福语
    private String client_ip;// Ip地址
    private String act_name;// 活动名称
    private String remark;// 备注
}
