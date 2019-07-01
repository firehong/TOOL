package com.cgmcomm.utils.yunxinim.model;

import lombok.Data;

/**
 * @auther Macro
 * @date 2019/6/19 13:47
 * @Description 响应基础数据
 */
public class ResponseInfo {

    private String name;
    private String token;
    private String accid;

    public ResponseInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }
}
