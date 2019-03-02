package com.mcfish.util.weixinpay;

import com.mcfish.util.weixinpay.model.RefundModel;
import com.mcfish.util.weixinpay.model.UnifiedorderModel;

public class Test {
	
	
	
	
	public static void main(String[] args) {
		//app支付
		UnifiedorderModel model = WxInit.getInstanceAppModel();
	    model.setOut_trade_no("545435454555354");
	    model.setTotal_fee(100);
	    model.setBody("微信订单");	
	    //HttpServletRequest req 可以不传 获取证书直接使用绝对路径 PayUtil-->ssl
	   // UnifiedorderResponse res = WxPay.payApp(req, model);
	    //微信公众号支付
	    UnifiedorderModel model1 = WxInit.getInstanceH5Model();
		 model.setOut_trade_no("545435454555354");
	     model.setTotal_fee(110);
	     model.setBody("微信订单");
	     model.setOpenid(""); //微信公众号授权openid			    
	     //PayH5Model res = WxPay.pay(req, model);
	    //小程序支付
		 UnifiedorderModel model2 = WxInit.getInstanceXiaoModel();
		 model.setOut_trade_no("545435454555354");
	     model.setTotal_fee(10);
	     model.setBody("微信订单");
	     model.setOpenid("");			    
	     //PayH5Model res = WxPay.pay(req, model);
	     
	     //退款
	     RefundModel  rdfund = new RefundModel();
		 rdfund.setOut_trade_no("545435454555354");
		 rdfund.setOut_refund_no("545435454555354");
		 rdfund.setTotal_fee(100);
		 rdfund.setRefund_fee(100);
		 rdfund.setRefund_desc("退款申请");
		 //Map<String, Object> res  = WxAplay.refundUtil(rdfund,req,2);
	}

}
