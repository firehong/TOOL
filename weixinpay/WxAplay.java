package com.share.util.weixinpay;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


import com.share.common.base.MyException;
import com.share.common.base.SystemError;
import com.share.util.weixinpay.config.BasicInfo;
import com.share.util.weixinpay.model.RefundModel;
import com.share.util.weixinpay.model.SendRedPackModel;
import com.share.util.weixinpay.model.TransfersModel;
import com.share.util.weixinpay.util.PayUtil;
import com.share.util.weixinpay.util.SignUtil;
import com.share.util.weixinpay.util.XMLUtil;

/**
 * 微信退款相关请求封装
 * @author TangLin
 * @date 2017年9月11日 下午5:10:11 
 * @version 1.0
 */

public class WxAplay {
	
	/******log record******/
	protected static Logger log = Logger.getLogger(WxAplay.class);
 
    /**
     * 发起微信红包退款
     * @param orderno 订单号
     * @param re_openid 退款者openid
     * @param amount 金额(分)
     * @param req
     * @return
     * @throws Exception
     */
    public static Map<String, Object>  WeRedUtils(String orderno,
    		 String re_openid,int amount, 
    		         HttpServletRequest req) throws Exception{
    	
    	Map<String, Object> mp = new HashMap<String, Object>();
    	SendRedPackModel sendRedPack = new SendRedPackModel();
        sendRedPack.setNonce_str(orderno);
        sendRedPack.setMch_id(BasicInfo.MchId);
        sendRedPack.setMch_billno(orderno);
        sendRedPack.setWxappid(BasicInfo.appID);
        sendRedPack.setSend_name(BasicInfo.Send_Name);
        sendRedPack.setTotal_num(1);
        sendRedPack.setAct_name("申请提现");
        sendRedPack.setWishing("红包");
        sendRedPack.setRemark("提现红包");
        sendRedPack.setClient_ip("8.8.8.8");
        
        sendRedPack.setRe_openid(re_openid);
        sendRedPack.setTotal_amount(amount);
        String sign = SignUtil.createSendRedPackOrderSign(sendRedPack);
        sendRedPack.setSign(sign);
        
        try{
        	 XMLUtil xmlUtil= new XMLUtil();
             xmlUtil.xstream().alias("xml", sendRedPack.getClass());
            
             String xml = xmlUtil.xstream().toXML(sendRedPack);
             String response = PayUtil.ssl(BasicInfo.httpurl, xml,req);
             System.out.println(response);
             Map<String, String> map = xmlUtil.parseXml(response);
             if("SUCCESS".equals(map.get("result_code"))){
            	 mp.put("stu", true);
            	 return mp;
             }else{
            	 mp.put("stu", false);
            	 mp.put("errMsg", map.get("return_msg"));
            	 log.error("微信红包退款失败》》"+"错误码:"+map.get("return_msg")+"  ;"
            	 		           + "描述:"+map.get("err_code_des"));
            	 return mp;
             }
        }catch (Exception e) {
        	   log.error("微信红包退款异常》》"+e);
               throw new MyException(SystemError.WX_RETREIEV_ERROR.getCode(), 
            		   SystemError.WX_RETREIEV_ERROR.getMessage());
        }
       
    }
    
   /**
    * 微信退款
    * @param rdfund
    * @param req
    * @return
    * @throws Exception
    */
   public static Map<String, Object>  refundUtil(RefundModel  rdfund
		   , HttpServletRequest req) throws Exception{
	   Map<String, Object> mp = new HashMap<String, Object>();
	   rdfund.setAppid(BasicInfo.appID);
	   rdfund.setMch_id(BasicInfo.MchId); 
	   rdfund.setNonce_str(PayUtil.getRandomStr());
	   rdfund.setSign_type("MD5");
	   rdfund.setSign(SignUtil.sign(SignUtil.createRefundSign(rdfund),BasicInfo.MchKey));
	   
	   try{
      	   XMLUtil xmlUtil= new XMLUtil();
           xmlUtil.xstream().alias("xml", rdfund.getClass());
          
           String xml = xmlUtil.xstream().toXML(rdfund);
           String response = PayUtil.ssl(BasicInfo.refundurl, xml,req);
           System.out.println(response);
           Map<String, String> map = xmlUtil.parseXml(response);
           if("SUCCESS".equals(map.get("result_code"))){
            	mp.put("stu", true);
                return mp;
           }else{
          	 mp.put("stu", false);
          	 mp.put("errMsg", map.get("return_msg"));
          	 mp.put("errDes", map.get("err_code_des"));
          	 log.error("微信退款失败》》"+"错误码:"+map.get("return_msg")+"  ;"
          	 	              	+ "描述:"+map.get("err_code_des"));
          	 return mp;
           }
      }catch (Exception e) {
    	     log.error("微信退款异常》》"+e);
             throw new MyException(SystemError.WX_RETREIEV_ERROR.getCode(), 
          		   SystemError.WX_RETREIEV_ERROR.getMessage());
      }
   }
   
   
   /**
    * 企业转账接口
    * @param transfer
    * @param req
    * @return
    * @throws Exception
    */
   public static Map<String, Object>  transfersUtil(TransfersModel  transfer
		   , HttpServletRequest req) throws Exception{
	   Map<String, Object> mp = new HashMap<String, Object>();
	   transfer.setMch_appid(BasicInfo.appID);
	   transfer.setMchid(BasicInfo.MchId);
	   transfer.setNonce_str(PayUtil.getRandomStr());
	   transfer.setCheck_name("NO_CHECK");
	   transfer.setSpbill_create_ip("127.0.0.1");
	   transfer.setSign(SignUtil.sign(SignUtil.createtransfersSign(transfer),BasicInfo.MchKey));
	   
	   try{
      	   XMLUtil xmlUtil= new XMLUtil();
           xmlUtil.xstream().alias("xml", transfer.getClass());
          
           String xml = xmlUtil.xstream().toXML(transfer);
           String response = PayUtil.ssl(BasicInfo.transfersurl, xml,req);
           System.out.println(response);
           Map<String, String> map = xmlUtil.parseXml(response);

           if("SUCCESS".equals(map.get("result_code"))){
            	mp.put("stu", true);
                return mp;
           }else{
          	 mp.put("stu", false);
          	 mp.put("errMsg", map.get("return_msg"));
          	 mp.put("errDes", map.get("err_code_des"));
          	 log.error("企业转账失败》》"+"错误码:"+map.get("return_msg")+"  ;"
          	 		   + "描述:"+map.get("err_code_des"));
          	 return mp;
           }
      }catch (Exception e) {
    	     log.error("企业转账异常》》"+e);
             throw new MyException(SystemError.WX_RETREIEV_ERROR.getCode(), 
          		   SystemError.WX_RETREIEV_ERROR.getMessage());
      }
   }
   
   

   
}
