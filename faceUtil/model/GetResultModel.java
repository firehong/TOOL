package com.cgmcomm.utils.faceUtil.model;

/**
 * @auther Macro
 * @date 2019/6/17 14:11
 * @Description 获取对比结果请求参数
 */
public class GetResultModel {
    /****/
    private String sign;
    /**签名算法版本号，目前仅支持："hmac_sha1"**/
    private String sign_version;
    /**get_biz_token接口获得的biz_token**/
    private String biz_token;
    /**表示返回数据的详细程度，取值如下：
     0：仅返回结论；
     1：默认值，返回结论与摘要信息**/
    private Integer verbose;

    public GetResultModel() {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_version() {
        return sign_version;
    }

    public void setSign_version(String sign_version) {
        this.sign_version = sign_version;
    }

    public String getBiz_token() {
        return biz_token;
    }

    public void setBiz_token(String biz_token) {
        this.biz_token = biz_token;
    }

    public Integer getVerbose() {
        return verbose;
    }

    public void setVerbose(Integer verbose) {
        this.verbose = verbose;
    }
}
