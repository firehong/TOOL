package com.mcfish.util.weixinpay;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.mcfish.base.exception.MyException;
import com.mcfish.base.exception.SystemError;
import com.mcfish.util.weixinpay.config.BasicInfo;
import com.mcfish.util.weixinpay.model.PayH5Model;
import com.mcfish.util.weixinpay.model.PayResponse;
import com.mcfish.util.weixinpay.model.UnifiedorderModel;
import com.mcfish.util.weixinpay.model.UnifiedorderResponse;
import com.mcfish.util.weixinpay.util.HttpUtil;
import com.mcfish.util.weixinpay.util.PayUtil;
import com.mcfish.util.weixinpay.util.SignUtil;
import com.mcfish.util.weixinpay.util.XMLUtil;


/**
 * 微信统一支付服务
 * 
 * @author Macro  2017年7月28日 上午11:32:26 
 * @version share 1.0
 */
public class WxPay{
    
	/******log record******/
	protected static Logger log = Logger.getLogger(WxPay.class);
	
	
	/**
	 * 发起统一支付请求
	 * @param req
	 * @param model
	 * @param type 1-公众号  2-小程序
	 * @return
	 * @throws Exception
	 */
	public static PayH5Model pay(
			           UnifiedorderModel model,Integer type) throws Exception {
	   //添加随机字符串和签名
	   model.setNonce_str(PayUtil.getRandomStr());
	   model.setSign(SignUtil.sign(SignUtil.createUnifiedSign(model),BasicInfo.MchKey));		
	   try{
		   //将序列化中的类全量名称，用别名替换,用于xml转换
      	   XMLUtil xmlUtil= new XMLUtil();
           xmlUtil.xstream().alias("xml", model.getClass());
           //对象转xml
           String xml = xmlUtil.xstream().toXML(model);
           String response = "";
           if(type==1) { //公众号
        	   response = PayUtil.ssl(BasicInfo.unifiedordersurl, xml,BasicInfo.MchId);
           }else if(type==2){//小程序
        	   response = PayUtil.ssl(BasicInfo.unifiedordersurl, xml,BasicInfo.XIAO_MchId);
           }else {
        	   throw new MyException(SystemError.PARAMETER_ERROR.getCode(), 
	          		   SystemError.PARAMETER_ERROR.getMessage());
           }	          		 
           //将返回xml转xml
      	   UnifiedorderResponse ret = (UnifiedorderResponse) 
    	    		XMLUtil.fromXML(response,UnifiedorderResponse.class); 
      	    
           if("SUCCESS".equals(ret.getResult_code())){
        	    PayH5Model res = buildPayH5Model(ret);
                return res;
           }else{
   
          	 log.error("微信下单失败》》"+"错误码:"+ret.getReturn_code()+"  ;"
          	 	              	+ "描述:"+ret.getReturn_msg());
          	 
          	 return new PayH5Model(ret.getReturn_code(), ret.getReturn_msg());
           }
	     }catch (Exception e) {
	    	     log.error("微信下单异常》》"+e);
	             throw new MyException(SystemError.PAY_ERROR.getCode(), 
	          		   SystemError.PAY_ERROR.getMessage());
	      }

	}
	
	/**
	 * APP微信支付
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static UnifiedorderResponse payApp(
	           UnifiedorderModel model) throws Exception {

			model.setNonce_str(PayUtil.getRandomStr());
			model.setSign(SignUtil.sign(SignUtil.createUnifiedSign(model),
					BasicInfo.APP_MchKey));		
			try{
				
			  XMLUtil xmlUtil= new XMLUtil();
			  xmlUtil.xstream().alias("xml", model.getClass());
			
			  String xml = xmlUtil.xstream().toXML(model);
			  String response = PayUtil.ssl(BasicInfo.unifiedordersurl, xml,
					  BasicInfo.APP_MchId);
			  
		      UnifiedorderResponse ret = (UnifiedorderResponse) 
		 		XMLUtil.fromXML(response,UnifiedorderResponse.class); 
		      
		      System.out.println("-------------------");
		      System.out.println(JSONObject.toJSONString(ret));	

			  if("SUCCESS".equals(ret.getResult_code())){
				  
				  //再次签名
				  Map<String, String> finalpackage = new TreeMap<String, String>();;
				  String timestamp = (System.currentTimeMillis()/1000)+"";
				  String noncestr = PayUtil.getRandomStr();
			     
			      finalpackage.put("appid", BasicInfo.APP_AppID); 
			      finalpackage.put("timestamp", timestamp);  
			      finalpackage.put("noncestr", noncestr);  		
			      finalpackage.put("prepayid", ret.getPrepay_id());
			      finalpackage.put("package", "Sign=WXPay"); 
			      finalpackage.put("partnerid",BasicInfo.APP_MchId); 
		
			      String sign = SignUtil.sign(finalpackage, BasicInfo.APP_MchKey);
			      	      
			      ret.setSign(sign);
			      ret.setNonce_str(noncestr);
			      ret.setTimestamp(timestamp);
				  		  
				  return ret;
			  }else{
				
				 log.error("微信下单失败》》"+"错误码:"+ret.getReturn_code()+"  ;"
				 	              	+ "描述:"+ret.getReturn_msg());
				 return ret;
			  }
			}catch (Exception e) {
				  log.error("微信下单异常》》"+e);
			      throw new MyException(SystemError.PAY_ERROR.getCode(), 
			   		   SystemError.PAY_ERROR.getMessage());
			}
     }
    
	/**
	 * pc网站微信支付
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static UnifiedorderResponse payPc(
	           UnifiedorderModel model) throws Exception {

			model.setNonce_str(PayUtil.getRandomStr());
			model.setSign(SignUtil.sign(SignUtil.createUnifiedSign(model),
					BasicInfo.XIAO_MchKey));		
			try{
				
			  XMLUtil xmlUtil= new XMLUtil();
			  xmlUtil.xstream().alias("xml", model.getClass());
		
			  String xml = xmlUtil.xstream().toXML(model);
			  String response = PayUtil.ssl(BasicInfo.unifiedordersurl, xml,
					  BasicInfo.XIAO_MchKey);		
			  
		      UnifiedorderResponse ret = (UnifiedorderResponse) 
		 		XMLUtil.fromXML(response,UnifiedorderResponse.class); 
		      
		      System.out.println("-------------------");
		      System.out.println(JSONObject.toJSONString(ret));	

			  if("SUCCESS".equals(ret.getResult_code())){			  		  
				  return ret;
			  }else{
				
				 log.error("微信下单失败》》"+"错误码:"+ret.getReturn_code()+"  ;"
				 	              	+ "描述:"+ret.getReturn_msg());
				 return ret;
			  }
			}catch (Exception e) {
				  log.error("微信下单异常》》"+e);
			      throw new MyException(SystemError.PAY_ERROR.getCode(), 
			   		   SystemError.PAY_ERROR.getMessage());
			}
     }
	
	/**
	 * 微信支付回调
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	public static PayResponse callBackWeiXin(HttpServletRequest req) 
			throws Exception {
		String data = HttpUtil.recieveData(req);
		//签名验证
		Map<String, String> map = XMLUtil.parseXml(data);
		String sign = SignUtil.sign(map, BasicInfo.MchKey);
		if(!sign.equals(map.get("sign"))){ //签名验证失败
			log.error("支付回调签名验证失败 \r\n "+data);
            return null;
		}
		PayResponse res = (PayResponse) XMLUtil.fromXML(data,PayResponse.class);   
		return res;
	}
	
	/**
	 * 构造H5支付所需参数
	 * @param response
	 * @param MchKey
	 * @return
	 */
	public static PayH5Model buildPayH5Model(UnifiedorderResponse response) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = PayUtil.getRandomStr();
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
        model.setPaySign(SignUtil.sign(map, BasicInfo.MchKey));

        return model;
	  }
	

	
}
