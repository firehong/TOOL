package com.cgmcomm.webapi.api.wechat.service;

import com.alibaba.fastjson.JSON;
import com.cgmcomm.webapi.common.base.service.BaseService;
import com.cgmcomm.webapi.utils.weixinpay.WxInit;
import com.cgmcomm.webapi.utils.weixinpay.model.CallBackWxModel;
import com.cgmcomm.webapi.utils.weixinpay.model.PayResponse;
import com.cgmcomm.webapi.utils.weixinpay.util.XMLUtil;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPay;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @auther Macro
 * @date 2020/4/26 9:22
 * @Description 微信支付回调处理
 */
@Service
@Slf4j
public class WeChatPayCallbackService extends BaseService {


    /**
     * @date 2020/4/26 9:35
     * @Description 微信支付回调处理
     * @Param
     */
    public void weChatPayCallBack(HttpServletRequest req, HttpServletResponse response){
        try {
            //支付回调信息处理
            String notifyData = XMLUtil.recieveData(req);
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);
            log.info("[ wechatPay ] 微信支付回调信息：{}", notifyMap);
            // 签名验证
            WXPay wxpay = WxInit.getInstancePay();
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
                PayResponse payVo = JSON.parseObject(JSON.toJSONString(notifyMap), PayResponse.class);
                // 业务处理



                // 响应微信处理成功
                String resXml = XMLUtil.ObjectToXML(CallBackWxModel.success());
                response.getWriter().write(resXml);
            }else{
                log.error("[ wechatPay ] 微信支付回调信息签名验证失败：{}", notifyMap);
            }
        }catch (Exception e){
            log.error("[ wechatPay ] 微信支付回调异常：{}",e.getMessage(),e);
        }
    }



}
