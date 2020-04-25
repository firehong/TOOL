package com.cgmcomm.webapi.utils.weixinpay;

import com.cgmcomm.webapi.utils.weixinpay.config.MyConfig;
import com.cgmcomm.webapi.utils.weixinpay.config.WxPayConfig;
import com.cgmcomm.webapi.utils.weixinpay.model.UnifiedorderModel;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPay;

/**
 * 微信支付相关初始化实例配置
 * 
 * @author wuhong  2017年7月28日 上午11:33:13 
 * @version share 1.0
 */
public class WxInit {
    
	/**H5请求实体**/
	private static UnifiedorderModel H5Model = null;
	/**小程序请求实体**/
	private static  UnifiedorderModel XiaoModel = null;
	/**APP请求实体**/
	private static  UnifiedorderModel AppModel = null;
	/**PC请求实体**/
	private static  UnifiedorderModel PcModel = null;
	/**请求主体**/
	private static WXPay wxpay = null;


	public static WXPay getInstancePay() throws Exception{
		if(wxpay==null) {
			MyConfig config = new MyConfig();
			wxpay = new WXPay(config);
			return wxpay;
		}else {
			return wxpay;
		}
	}
	
	/**
	 * 获取H5支付实体
	 * @return
	 */
	public static UnifiedorderModel getInstanceH5Model() {
		if(H5Model==null) {
			H5Model = new UnifiedorderModel();
			//异步通知地址
			H5Model.setNotify_url(WxPayConfig.NotifyUrl);
			//请求方式
			H5Model.setTrade_type("JSAPI");
			H5Model.setSpbill_create_ip("8.8.8.8");
	        return H5Model;
		}else {
			return H5Model;
		}
	}
	
	/**
	 * 获取小程序支付实体
	 * @return
	 */
	public static UnifiedorderModel getInstanceXiaoModel() {
		if(XiaoModel==null) {
			XiaoModel = new UnifiedorderModel();
			//异步通知地址
			XiaoModel.setNotify_url(WxPayConfig.NotifyUrl);
			//请求方式
			XiaoModel.setTrade_type("JSAPI");
			XiaoModel.setSpbill_create_ip("8.8.8.8");
	        return XiaoModel;
		}else {
			return XiaoModel;
		}
	}
	
	/**
	 * 获取app支付实体
	 * @return
	 */
	public static UnifiedorderModel getInstanceAppModel() {
		if(AppModel==null) {
			AppModel = new UnifiedorderModel();
			//异步通知地址
			AppModel.setNotify_url(WxPayConfig.NotifyUrl);
			//请求方式
			AppModel.setTrade_type("APP");
			AppModel.setSpbill_create_ip("8.8.8.8");
	        return AppModel;
		}else {
			return AppModel;
		}
	}
	
	/**
	 * 获取PC支付实体
	 * @return
	 */
	public static UnifiedorderModel getInstancePcModel() {
		if(PcModel==null) {
			PcModel = new UnifiedorderModel();
			//异步通知地址
			PcModel.setNotify_url(WxPayConfig.NotifyUrl);
			//请求方式
			PcModel.setTrade_type("NATIVE");
			PcModel.setSpbill_create_ip("8.8.8.8");
	        return PcModel;
		}else {
			return PcModel;
		}
	}
	

	
}
