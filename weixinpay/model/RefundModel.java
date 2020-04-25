package com.cgmcomm.webapi.utils.weixinpay.model;


import lombok.Data;

/**
 * 微信退款实体
 * 
 * @author wuhong  2017年11月2日 下午2:13:44 
 * @version 源佳 1.0
 */
@Data
public class RefundModel {
    
	/**公众号appid*/
	private String appid;
	/**商户号*/
	private String mch_id;
	/**随机字符串*/
	private String nonce_str;
	/**签名*/
	private String sign;
	/**签名方式*/
	private String sign_type;
	/**支付订单号*/
	private String out_trade_no;
	/**退款订单号*/
	private String out_refund_no;
	/**总金额*/
	private Integer total_fee;
	/**退款金额*/
	private Integer refund_fee;
	/**退款原因*/
	private String refund_desc;

}
