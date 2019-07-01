package com.cgmcomm.utils.yunxinim.model;

/**
 * @auther Macro
 * @date 2019/6/19 13:48
 * @Description 响应数据
 */
public class ImResponse {
    /**错误码**/
    private Integer code;
    /**响应信息**/
    private ResponseInfo info;
    /**错误描述**/
    private String desc;

    public ImResponse() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ResponseInfo getInfo() {
        return info;
    }

    public void setInfo(ResponseInfo info) {
        this.info = info;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
