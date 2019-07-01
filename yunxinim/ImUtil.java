package com.cgmcomm.utils.yunxinim;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgmcomm.cgmClient.config.ImConfig;
import com.cgmcomm.cgmClient.utils.LogUtils;
import com.cgmcomm.utils.yunxinim.config.RequestUrl;
import com.cgmcomm.utils.yunxinim.model.RequestModel;
import com.cgmcomm.utils.yunxinim.model.ImResponse;
import com.cgmcomm.utils.yunxinim.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther Macro
 * @date 2019/6/19 13:42
 * @Description 网易IM云创建工具
 */
public class ImUtil {

    /**
     * @date 2019/6/19 14:03
     * @Description 创建网易IM用户
     * @Param
     */
    public  static ImResponse createMember(RequestModel model){
        try {
            //封装map
            Map map = new HashMap();
            map.put("accid",model.getAccid());
            map.put("token",model.getToken());
            map.put("name",model.getName());
            map.put("props",model.getProps());
            map.put("icon",model.getIcon());
            map.put("sign",model.getSign());
            map.put("email",model.getEmail());
            map.put("birth",model.getBirth());
            map.put("mobile",model.getMobile());
            map.put("gender",model.getGender());
            map.put("ex",model.getEx());
            String resStr = HttpUtil.post(RequestUrl.CREATE_MEMBER, map);
            //转对象返回结果
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            ImResponse res = JSONObject.toJavaObject(jsonObject, ImResponse.class);
            return  res;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 注册网易IM异常==>>ERROR:{},请求参数：{}",e,JSON.toJSONString(model));
            return null;
        }
    }

    /**
     * @date 2019/6/19 14:34 
     * @Description 网易云通信ID更新
     * @Param
     */
    public  static ImResponse update(RequestModel model){
        try {
            //封装map
            Map map = new HashMap();
            map.put("accid",model.getAccid());
            map.put("token",model.getToken());
            map.put("props",model.getProps());
            String resStr = HttpUtil.post(RequestUrl.UPDATE, map);
            //转对象返回结果
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            ImResponse res = JSONObject.toJavaObject(jsonObject, ImResponse.class);
            return  res;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 网易IM通信ID更新异常==>>ERROR:{},请求参数：{}",e,JSON.toJSONString(model));
            return null;
        }
    }

    /**
     * @date 2019/6/19 14:34
     * @Description 更新并获取新token
     * @Param
     */
    public  static ImResponse refreshToken(RequestModel model){
        try {
            //封装map
            Map map = new HashMap();
            map.put("accid",model.getAccid());
            String resStr = HttpUtil.post(RequestUrl.REFRESH_TOKEN, map);
            //转对象返回结果
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            ImResponse res = JSONObject.toJavaObject(jsonObject, ImResponse.class);
            return  res;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 网易IM更新并获取新token异常==>>ERROR:{},请求参数：{}",e,JSON.toJSONString(model));
            return null;
        }
    }

    /**
     * @date 2019/6/19 14:34
     * @Description 封禁网易云通信ID
     * @Param
     */
    public  static ImResponse block(RequestModel model){
        try {
            //封装map
            Map map = new HashMap();
            map.put("accid",model.getAccid());
            String resStr = HttpUtil.post(RequestUrl.BLOCK, map);
            //转对象返回结果
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            ImResponse res = JSONObject.toJavaObject(jsonObject, ImResponse.class);
            return  res;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 封禁网易云通信ID异常==>>ERROR:{},请求参数：{}",e,JSON.toJSONString(model));
            return null;
        }
    }

    /**
     * @date 2019/6/19 14:34
     * @Description 解禁网易云通信ID
     * @Param
     */
    public  static ImResponse unblock(RequestModel model){
        try {
            //封装map
            Map map = new HashMap();
            map.put("accid",model.getAccid());
            String resStr = HttpUtil.post(RequestUrl.UNBLOCK, map);
            //转对象返回结果
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            ImResponse res = JSONObject.toJavaObject(jsonObject, ImResponse.class);
            return  res;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 解禁网易云通信ID异常==>>ERROR:{},请求参数：{}",e,JSON.toJSONString(model));
            return null;
        }
    }

}
