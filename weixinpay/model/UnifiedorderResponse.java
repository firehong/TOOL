package com.cgmcomm.webapi.utils.weixinpay.model;

import lombok.Data;

/**
 * 微信下单预付单返回信息
 * 
 * @author wuhong  2017年11月27日 下午3:53:24 
 * @version share 1.0
 */
@Data
public class UnifiedorderResponse{
    
	/**预付单信息**/
	private String prepay_id;
	/**签名**/
	private String nonce_str;
	/**公众号appid**/
	private String appid;
	/**签名**/
	private String sign;
	/**请求方式**/
	private String trade_type;
	/**商户号id**/
	private String mch_id;
	
	/**返回提示信息**/
	private String return_msg;
	/**结果码**/
	private String result_code;
	/**返回码**/
	private String return_code;
	/**支付二维码**/
	private String code_url;
	/**时间戳**/
	private String timestamp;

}
