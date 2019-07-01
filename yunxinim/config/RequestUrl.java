package com.cgmcomm.utils.yunxinim.config;

/**
 * @auther Macro
 * @date 2019/6/17 12:47
 * @Description 网易IM API请求路径
 */
public class RequestUrl {

    /**创建网易用户地址**/
    public static final String CREATE_MEMBER = "https://api.netease.im/nimserver/user/create.action";
    /**网易云通信ID更新请求路径**/
    public static final String UPDATE = "https://api.netease.im/nimserver/user/update.action";
    /**更新并获取新token请求路径**/
    public static final String REFRESH_TOKEN = "https://api.netease.im/nimserver/user/refreshToken.action";
    /**封禁网易云通信ID**/
    public static final String BLOCK = "https://api.netease.im/nimserver/user/block.action";
    /**解禁网易云通信ID**/
    public static final String UNBLOCK = "https://api.netease.im/nimserver/user/unblock.action";

}
