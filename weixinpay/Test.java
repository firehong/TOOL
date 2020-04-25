package com.cgmcomm.webapi.utils.weixinpay;

import com.cgmcomm.webapi.utils.weixinpay.model.PayH5Model;
import com.cgmcomm.webapi.utils.weixinpay.model.UnifiedorderModel;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPay;

import java.util.HashMap;
import java.util.Map;


public class Test {
	

	public static void main(String[] args) throws Exception{
		//js支付
		UnifiedorderModel unifiedorderModel = WxInit.getInstanceH5Model();
		unifiedorderModel.setBody("测试支付");
		unifiedorderModel.setOut_trade_no("111111");
		unifiedorderModel.setTotal_fee(100);
		unifiedorderModel.setOpenid("oW-Ai1gU-zCmjkg2nsaSg5JnUKpQ");
		try {
			// 再次签名
			PayH5Model payH5Model = WxPay.pay(unifiedorderModel);
			System.out.println(payH5Model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//test();
	}

	public static void test() throws Exception{
		WXPay wxpay = WxInit.getInstancePay();
		Map<String, String> data = new HashMap<String, String>();
		data.put("body", "腾讯充值中心-QQ会员充值");
		data.put("out_trade_no", "2016090910595900000012");
		data.put("device_info", "");
		data.put("fee_type", "CNY");
		data.put("total_fee", "1");
		data.put("spbill_create_ip", "123.12.12.123");
		data.put("notify_url", "http://www.example.com/wxpay/notify");
		data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
		data.put("product_id", "12");

		try {
			Map<String, String> resp = wxpay.unifiedOrder(data);
			System.out.println(resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
