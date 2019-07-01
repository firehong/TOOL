package com.cgmcomm.utils.faceUtil.model;

/**
 * @auther Macro
 * @date 2019/6/17 12:50
 * @Description 获取biz_token响应参数
 */
public class BizTokenResponse {

    /**用于区分每一次请求的唯一的字符串**/
    private String request_id;
    /**整个请求所花费的时间，单位为毫秒**/
    private Integer time_used;
    /**该返回值作为调用SDK的输入参数**/
    private String biz_token;
    /***具体返回内容见错误码列表*/
    private String error;

    public BizTokenResponse() {

    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Integer getTime_used() {
        return time_used;
    }

    public void setTime_used(Integer time_used) {
        this.time_used = time_used;
    }

    public String getBiz_token() {
        return biz_token;
    }

    public void setBiz_token(String biz_token) {
        this.biz_token = biz_token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
