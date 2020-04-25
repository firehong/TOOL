package com.cgmcomm.webapi.utils.weixinpay.model;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


/**
 * 微信回调实体
 * 
 * @author wuhong  2017年11月27日 下午2:00:18 
 * @version share 1.0
 */
@Root(name = "xml", strict = false)
@Data
public class PayResponse {


	@Element(name = "return_code")
    private String return_code;

    @Element(name = "return_msg", required = false)
    private String return_msg;

    /** 以下字段在return_code为SUCCESS的时候有返回. */
    @Element(name = "appid", required = false)
    private String appid;

    @Element(name = "mch_id", required = false)
    private String mch_id;

    @Element(name = "device_info", required = false)
    private String device_info;

    @Element(name = "nonce_str", required = false)
    private String nonce_str;

    @Element(name = "sign", required = false)
    private String sign;

    @Element(name = "result_code", required = false)
    private String result_code;

    @Element(name = "err_code", required = false)
    private String err_code;

    @Element(name = "err_code_des", required = false)
    private String err_code_des;

    @Element(name = "openid", required = false)
    private String openid;

    @Element(name = "is_subscribe", required = false)
    private String is_subscribe;

    @Element(name = "trade_type", required = false)
    private String trade_type;

    @Element(name = "bank_type", required = false)
    private String bank_type;

    @Element(name = "total_fee", required = false)
    private Integer total_fee;

    @Element(name = "fee_type", required = false)
    private String fee_type;

    @Element(name = "cash_fee", required = false)
    private String cash_fee;

    @Element(name = "cash_fee_type", required = false)
    private String cash_fee_type;

    @Element(name = "coupon_fee", required = false)
    private String coupon_fee;

    @Element(name = "coupon_count", required = false)
    private String coupon_count;

    @Element(name = "transaction_id", required = false)
    private String transaction_id;

    @Element(name = "out_trade_no", required = false)
    private String out_trade_no;

    @Element(name = "attach", required = false)
    private String attach;

    @Element(name = "time_end", required = false)
    private String time_end;

}
