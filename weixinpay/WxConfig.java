package com.share.util.weixinpay;

import javax.servlet.http.HttpServletRequest;

import com.share.util.weixinpay.config.BasicInfo;
import com.share.util.weixinpay.model.UnifiedorderModel;

/**
 * 微信支付相关配置
 * 
 * @author wuhong  2017年7月28日 上午11:33:13 
 * @version share 1.0
 */
public class WxConfig {
    
	/**H5请求实体**/
	private static  UnifiedorderModel H5Model = null;
	/**小程序请求实体**/
	private static  UnifiedorderModel XiaoModel = null;
	/**APP请求实体**/
	private static  UnifiedorderModel AppModel = null;

	
	/**
	 * 获取H5支付实体
	 * @return
	 */
	public static UnifiedorderModel getInstanceH5Model() {
		if(H5Model==null) {
			H5Model = new UnifiedorderModel();
			//异步通知地址
			H5Model.setNotify_url(BasicInfo.NotifyUrl);
	         //公众号appId
			H5Model.setAppid(BasicInfo.appID);
	         //商户号
			H5Model.setMch_id(BasicInfo.MchId);
			//请求方式
			H5Model.setTrade_type("JSAPI");
			//加密方式
			H5Model.setSign_type("MD5");
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
			XiaoModel.setNotify_url(BasicInfo.NotifyUrl);
	        //小程序appId
			XiaoModel.setAppid(BasicInfo.XIAO_AppID);
	        //商户号
			XiaoModel.setMch_id(BasicInfo.MchId);
			//请求方式
			XiaoModel.setTrade_type("JSAPI");
			//加密方式
			XiaoModel.setSign_type("MD5");
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
			AppModel = new UnifiedorderModel();
			//异步通知地址
			AppModel.setNotify_url(BasicInfo.NotifyUrl);
	         //app应用appId
			AppModel.setAppid(BasicInfo.APP_AppID);
	         //商户号
			AppModel.setMch_id(BasicInfo.APP_MchId);
			//请求方式
			AppModel.setTrade_type("APP");
			//加密方式
			AppModel.setSign_type("MD5"); 
			AppModel.setSpbill_create_ip("8.8.8.8");
	        return AppModel;
		}else {
			return AppModel;
		}
	}
	

	
}
