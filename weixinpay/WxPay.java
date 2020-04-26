package com.cgmcomm.webapi.utils.weixinpay;

import com.alibaba.fastjson.JSON;
import com.cgmcomm.common.exception.MyException;
import com.cgmcomm.common.exception.SystemError;
import com.cgmcomm.common.util.SignUtil;
import com.cgmcomm.webapi.utils.weixinpay.config.WxPayConfig;
import com.cgmcomm.webapi.utils.weixinpay.model.PayH5Model;
import com.cgmcomm.webapi.utils.weixinpay.model.RefundModel;
import com.cgmcomm.webapi.utils.weixinpay.model.UnifiedorderModel;
import com.cgmcomm.webapi.utils.weixinpay.model.UnifiedorderResponse;
import com.cgmcomm.webapi.utils.weixinpay.util.MapUtil;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPay;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微信统一支付服务
 *
 * @author Macro  2017年7月28日 上午11:32:26
 * @version share 1.0
 */
@Slf4j
public class WxPay{

	/**
	 * H5类型支付 发起统一支付请求
	 * @param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static PayH5Model pay(UnifiedorderModel model) throws Exception {
	   try{
		   WXPay wxpay = WxInit.getInstancePay();
		   // 对象转map
		   Map<String, String> param= MapUtil.convertToMap(model);
		   log.info("[ wxpay ] 微信JSAPI支付请求参数：{}",param);
		   // 请求
		   Map<String, String> retMap = wxpay.unifiedOrder(param);
           if("SUCCESS".equals(retMap.get("result_code"))){
			   	UnifiedorderResponse ret = JSON.parseObject(JSON.toJSONString(retMap), UnifiedorderResponse.class);
			   	// 再次签名;
                return buildPayH5Model(ret);
           }else{
				log.error("微信下单失败》》返回信息：{},错误码:{}，描述:{}",retMap,retMap.get("result_code"),retMap.get("result_msg"));
				return new PayH5Model(retMap.get("result_code"), retMap.get("err_code_des"));
           }
	   }catch (Exception e) {
			log.error("微信下单异常:{}",e.getMessage(),e);
			throw new MyException(SystemError.OPERATION_FAIL.getCode(), SystemError.OPERATION_FAIL.getMessage());
	   }
	}

	/**
	 * APP微信支付
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static UnifiedorderResponse payApp(UnifiedorderModel model){
		try{
			WXPay wxpay = WxInit.getInstancePay();
			// 对象转map
			Map<String, String> param= MapUtil.convertToMap(model);
			log.info("[ wxpay ] App微信支付请求参数：{}",param);
			// 请求
			Map<String, String> retMap = wxpay.unifiedOrder(param);
			if("SUCCESS".equals(retMap.get("result_code"))){
				UnifiedorderResponse ret = JSON.parseObject(JSON.toJSONString(retMap), UnifiedorderResponse.class);
				//再次签名
				Map<String, String> finalpackage = new TreeMap<String, String>();;
				String timestamp = (System.currentTimeMillis()/1000)+"";
				String noncestr = WXPayUtil.generateNonceStr();
				finalpackage.put("appid", WxPayConfig.APP_AppID);
				finalpackage.put("timestamp", timestamp);
				finalpackage.put("noncestr", noncestr);
				finalpackage.put("prepayid", ret.getPrepay_id());
				finalpackage.put("package", "Sign=WXPay");
				finalpackage.put("partnerid",WxPayConfig.APP_MchId);
				String sign = SignUtil.sign(finalpackage, WxPayConfig.APP_MchKey);
				ret.setSign(sign);
				ret.setNonce_str(noncestr);
				ret.setTimestamp(timestamp);
				return ret;
			}else{
				log.error("[ wxpay ] 微信下单失败》》返回信息：{},错误码:{}，描述:{}",retMap,retMap.get("result_code"),retMap.get("result_msg"));
				throw new MyException(SystemError.OPERATION_FAIL.getCode(), retMap.get("result_msg"));
			}
		}catch (Exception e) {
			log.error("[ wxpay ] 微信下单异常，请求参数：{}，异常信息{}", model, e.getMessage(),e);
			throw new MyException(SystemError.OPERATION_FAIL.getCode(), SystemError.OPERATION_FAIL.getMessage());
		}
	}

	/**
	 * 微信退款
	 * @param rdfund
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object>  refund(RefundModel rdfund){
		try {
			Map<String, Object> mp = new HashMap<String, Object>();
			WXPay wxpay = WxInit.getInstancePay();
			// 对象转map
			Map<String, String> param= MapUtil.convertToMap(rdfund);
			log.info("[ wxpay ] 微信JSAPI支付请求参数：{}",param);
			// 请求
			Map<String, String> retMap = wxpay.refund(param);
			if("SUCCESS".equals(retMap.get("result_code"))){
				mp.put("flag", true);
				return mp;
			}else{
				mp.put("flag", false);
				mp.put("errMsg", retMap.get("return_msg"));
				mp.put("errDes", retMap.get("err_code_des"));
				log.error("[ wxpay ] 微信退款失败》》返回信息：{},错误码:{}，描述:{}",retMap,retMap.get("result_code"),retMap.get("err_code_des"));
				return mp;
			}
		}catch (Exception e){
			log.error("[ wxpay ] 微信退款异常，请求参数：{}，异常信息{}", rdfund, e.getMessage(),e);
			throw new MyException(SystemError.OPERATION_FAIL.getCode(), SystemError.OPERATION_FAIL.getMessage());
		}
	}

	/**
	 * 构造H5支付所需参数
	 * @param response
	 * @param
	 * @return
	 */
	public static PayH5Model buildPayH5Model(UnifiedorderResponse response) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = WXPayUtil.generateNonceStr();
        String packAge = "prepay_id=" + response.getPrepay_id();
        String signType = "MD5";
        //先构造要签名的map
        Map<String, String> map = new HashMap<>();
        map.put("appId", response.getAppid());
        map.put("timeStamp", timeStamp);
        map.put("nonceStr", nonceStr);
        map.put("package", packAge);
        map.put("signType", signType);

        PayH5Model model = new PayH5Model();
        model.setAppId(response.getAppid());
        model.setTimeStamp(timeStamp);
        model.setNonceStr(nonceStr);
        model.setPackAge(packAge);
        model.setSignType(signType);
        model.setPaySign(SignUtil.sign(map, WxPayConfig.MchKey));
        return model;
	}



}
