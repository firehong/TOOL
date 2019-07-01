package com.cgmcomm.utils.faceUtil;

import com.cgmcomm.cgmClient.config.FaceConfig;
import com.cgmcomm.cgmClient.utils.LogUtils;
import com.cgmcomm.utils.faceUtil.model.BizTokenModel;
import com.cgmcomm.utils.faceUtil.model.GetResultModel;
import com.cgmcomm.utils.faceUtil.util.HmacSha1Sign;

/**
 * @auther Macro
 * @date 2019/6/17 13:02
 * @Description 实体参数初始化
 */
public class ParamInit {

    /**
     * @date 2019/6/17 13:15 
     * @Description 
     * @Param 获取getbizToken实体
     */
    public static BizTokenModel getBizTokenModelInstance(){
        //设置签名
        try {
            BizTokenModel model = new BizTokenModel();
            //设置基础参数
            model.setSign_version("hmac_sha1");
            model.setLiveness_type("meglive");
            model.setComparison_type(1);
            model.setLiveness_timeout(60);
            model.setLiveness_action_count(3);
            model.setSecurity_level(2);
            model.setForce_compare(0);
            model.setMulti_oriented_detection(1);
            //设置签名
            model.setSign(HmacSha1Sign.genSign(FaceConfig.INSTANCE.getApiKey(),FaceConfig.INSTANCE.getSecretKey(), 180L));
            //model.setSign(HmacSha1Sign.genSign("1q08du554jXNHqnNKOLrEU0qHMWw3WL-","nyiXUQUtXulRLEOJ6kHsXmE9qsHY2OfI", 180L));
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.getLogger().error("key="+FaceConfig.INSTANCE.getApiKey());
            LogUtils.getLogger().error("[API]：GET_BIZ_TOKEN_MODEL-ERROR ===>{},key:{}",e,FaceConfig.INSTANCE.getApiKey());
            return null;
        }
    }

    /**
     * @date 2019/6/17 13:15
     * @Description
     * @Param 获取getbizToken实体
     */
    public static GetResultModel GetResultModelInstance(){
        //设置签名
        try {
            GetResultModel model = new GetResultModel();
            //设置基础参数
            model.setSign_version("hmac_sha1");
            //设置签名
            model.setSign(HmacSha1Sign.genSign(FaceConfig.INSTANCE.getApiKey(),FaceConfig.INSTANCE.getSecretKey(), 180L));
           // model.setSign(HmacSha1Sign.genSign("1q08du554jXNHqnNKOLrEU0qHMWw3WL-","nyiXUQUtXulRLEOJ6kHsXmE9qsHY2OfI", 180L));
            model.setVerbose(1);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.getLogger().error("[API]：GET_RESULT_MODEL-ERROR ===>{}",e);
            return null;
        }
    }

}
