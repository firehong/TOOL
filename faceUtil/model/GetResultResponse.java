package com.cgmcomm.utils.faceUtil.model;

import com.alibaba.fastjson.JSON;

/**
 * @auther Macro
 * @date 2019/6/17 14:15
 * @Description 人脸对比结果响应实体
 */
public class GetResultResponse {
    /**用于区分每一次请求的唯一的字符串。此字符串可以用于后续数据反查。此字段必定返回。**/
    private String request_id;
    /**get_biz_token 接口传入的业务流水号**/
    private String biz_no;
    /**整个请求所花费的时间，单位为毫秒。此字段必定返回。**/
    private Integer time_used;
    /**通过get_biz_token获取的唯一串码**/
    private String biz_token;
    /**表示本次验证的结果状态码，参见下面详述**/
    private Integer result_code;
    /**开发者可通过此字段信息知晓具体的原因，参见下面详述**/
    private String result_message;
    /**人脸比对的详细结果**/
    private JSON verification;
    /**活体照片, 字段如下**/
    private JSON images;
    /**HTTP status code非200时返回，参见下面详述(HTTP状态码和对应的ERROR字段说明)**/
    private String error;

    public GetResultResponse() {
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getBiz_no() {
        return biz_no;
    }

    public void setBiz_no(String biz_no) {
        this.biz_no = biz_no;
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

    public Integer getResult_code() {
        return result_code;
    }

    public void setResult_code(Integer result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public JSON getVerification() {
        return verification;
    }

    public void setVerification(JSON verification) {
        this.verification = verification;
    }

    public JSON getImages() {
        return images;
    }

    public void setImages(JSON images) {
        this.images = images;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
