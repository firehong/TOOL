package com.cgmcomm.utils.yunxinim.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019/6/19 13:49
 * @Description 网易IM 响应错误码
 */
public class  ImErrorCode {

    public static Map error = new HashMap();

    static {
        error.put(200,"操作成功");
        error.put(201,"客户端版本不对，需升级sdk");
        error.put(301,"被封禁");
        error.put(302,"用户名或密码错误");
        error.put(315,"IP限制");
        error.put(403,"非法操作或没有权限");
        error.put(404,"对象不存在");
        error.put(405,"参数长度过长");
        error.put(406,"对象只读");
        error.put(408,"客户端请求超时");
        error.put(413,"验证失败(短信服务)");
        error.put(414,"参数错误");
        error.put(415,"客户端网络问题");
        error.put(416,"频率控制");
        error.put(417,"重复操作");
        error.put(418,"通道不可用(短信服务)");
        error.put(419,"数量超过上限");
        error.put(422,"账号被禁用");
        error.put(423,"帐号被禁言");
        error.put(431,"HTTP重复请求");
        error.put(500,"服务器内部错误");
        error.put(503,"服务器繁忙");
        error.put(508,"消息撤回时间超限");
        error.put(509,"无效协议");
        error.put(514,"服务不可用");
    }

}
