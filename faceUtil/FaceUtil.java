package com.cgmcomm.utils.faceUtil;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgmcomm.cgmClient.utils.LogUtils;
import com.cgmcomm.utils.faceUtil.config.RequestUrl;
import com.cgmcomm.utils.faceUtil.model.BizTokenModel;
import com.cgmcomm.utils.faceUtil.model.BizTokenResponse;
import com.cgmcomm.utils.faceUtil.model.GetResultModel;
import com.cgmcomm.utils.faceUtil.model.GetResultResponse;
import com.cgmcomm.utils.HttpUtil;

/**
 * @auther Macro
 * @date 2019/6/17 12:45
 * @Description faceId 旷视API
 */
public class FaceUtil {
    
    /**
     * @date 2019/6/17 14:21
     * @Description 获取biz_token
     * @Param
     */
    public static BizTokenResponse getBizToken(BizTokenModel model){
        try{
            String resStr = HttpUtil.post(RequestUrl.GET_BIZ_TOKEN, JSON.toJSONString(model), null);
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            BizTokenResponse res = JSONObject.toJavaObject(jsonObject, BizTokenResponse.class);
            return res;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 获取faceId旷视token==>>ERROR:{}",e);
            return null;
        }
    }

    /**
     * @date 2019/6/17 15:08
     * @Description 获取对比结果
     * @Param
     */
    public static GetResultResponse getResult(GetResultModel model){
        try{
            //请求地址拼接
            StringBuilder getUrl = new StringBuilder();
            getUrl.append(RequestUrl.GET_RESULT);
            getUrl.append("?biz_token=");
            getUrl.append(model.getBiz_token());
            getUrl.append("&verbose=");
            getUrl.append(model.getVerbose());
            getUrl.append("&sign_version=");
            getUrl.append(model.getSign_version());
            getUrl.append("&sign=");
            getUrl.append(model.getSign());
            //发起请求
            String resStr = HttpUtil.get(getUrl.toString(),null);
            //转对象返回结果
            JSONObject jsonObject = JSONObject.parseObject(resStr);
            GetResultResponse res = JSONObject.toJavaObject(jsonObject, GetResultResponse.class);
            return res;
        }catch (Exception e){
            LogUtils.getLogger().error("[API] 获取faceId旷视人脸验证结果==>>ERROR:{}",e);
            return null;
        }
    }



}
