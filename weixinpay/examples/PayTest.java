package com.share.app.wallet.controller;

import com.share.util.weixinpay.WxConfig;
import com.share.util.weixinpay.WxPay;
import com.share.util.weixinpay.model.UnifiedorderModel;
import com.share.util.weixinpay.model.UnifiedorderResponse;

public class PayTest {

	
	public static void main(String[] args) {
		UnifiedorderModel model = WxConfig.getInstanceAppModel();
	    model.setOut_trade_no("558445544");
	    model.setTotal_fee(100);
	    model.setBody("微信订单");			    
	    UnifiedorderResponse res = WxPay.payApp(HttpServletRequest, model);	
	}
}
