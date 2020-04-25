package com.cgmcomm.webapi.utils.weixinpay.config;

import com.cgmcomm.webapi.utils.weixinpay.wxsdk.IWXPayDomain;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPayConfig;
import com.cgmcomm.webapi.utils.weixinpay.wxsdk.WXPayConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
public class MyConfig extends WXPayConfig {

    @Override
    protected String getAppID() {
        return WxPayConfig.appID;
    }

    @Override
    protected String getMchID() {
        return WxPayConfig.MchId;
    }

    @Override
    public String getKey() {
        return WxPayConfig.MchKey;
    }

    @Override
    protected InputStream getCertStream() {
        try {
            ByteArrayInputStream certBis = new ByteArrayInputStream(getCertBis());
            return certBis;
        }catch (Exception e){
            log.error("[ wxpay ] 微信商户号证书读取异常：{}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }


    private byte[] getCertBis() throws Exception {
        try {
            byte[] certData;
            String certPath = WxPayConfig.linux_KeyPath;
            File file = new File(certPath);
            InputStream certStream = new FileInputStream(file);
            certData = new byte[(int) file.length()];
            certStream.read(certData);
            certStream.close();
            return certData;
        }catch (Exception e){
            log.error("[ wxpay ] 微信商户号证书读取异常：{}", e.getMessage(), e);
            return null;
        }
    }


}
