package com.cgmcomm.webapi.utils.weixinpay.model;

import lombok.Data;

/**
 * 统一下单支付实体
 * 
 * @author wuhong  2017年11月27日 下午1:51:03 
 * @version share 1.0
 */
@Data
public class UnifiedorderModel {

	/**商品描述：腾讯充值中心-QQ会员充值*/
	private String body;	
	/**商品详情**/
	private String detail;
	/**附加数据**/
	private String attach;
	/**支付订单号*/
	private String out_trade_no;
	/**总金额(分)*/
	private Integer total_fee;
	/**终端IP(8.8.8.8)*/
	private String spbill_create_ip;
	/**异步通知地址*/
	private String notify_url;
	/**交易类型**/
	private String trade_type;
	/**用户标识**/
	private String openid;
	/**商品id**/
	private String product_id;
}
