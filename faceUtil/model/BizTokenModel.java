package com.cgmcomm.utils.faceUtil.model;

import java.io.File;

/**
 * @auther Macro
 * @date 2019/6/17 11:22
 * @Description 获取biz_token请求参数实体
 */
public class BizTokenModel {
    /**签名**/
    private String sign;
    /**签名类型"hmac_sha1"**/
    private String sign_version;
    /**活体类型，目前仅支持："meglive"（动作活体）**/
    private String liveness_type;
    /**本次身份验证服务的类型。
     0：表示活体照片和参考照片进行比对(人脸比对)。
     1：表示活体照片和第三方权威数据进行比对（人脸核身）**/
    private Integer comparison_type;
    /**姓名**/
    private String idcard_name;
    /**身份证号**/
    private String idcard_number;
    /**参考照片1。
     说明：
     - 当comparison_type = 0时，支持用户传1～2张参考照片（最少传入1张）
     - 当comparison_type = 1时，用户也可以传1～2张参考照片
     - 如果在任一张参考照片图中都没有找到人脸，将返回错误码400(NO_FACE_FOUND:image_ref1)；
     - 如果这些图片中任一张中有多张脸，将选择最大人脸进行比对。**/
    private File image_ref1;
    /**参考照片2，规则参数上面的描述**/
    private File image_ref2;
    /**用于标志本次识别对应的用户的唯一ID，不长于128字节。
     * 建议您对来自同一用户的比对请求使用同样的ID，这非常有利于您反查验证结果以及获得更好的监控报表体验。**/
    private String uuid;
    /**客户业务流水号，建议设置为您的业务相关的流水串号并且唯一，不长于128字节**/
    private String biz_no;
    /**活体检测超时时间，默认值为60s，可以设置为5～60之间的整数。若未在规定时间完成操作，则本次活体失败**/
    private Integer liveness_timeout;
    /**动作活体时动作个数，默认值为3（即3个随机动作），可选值1，2，3**/
    private Integer liveness_action_count;
    /**表示对比对结果的严格程度限制，请根据您的场景，选择安全规则，越严格，准确性要求越高，通过率也会相应下降
     1：宽松(误识率为千分之一)；
     2：标准(误识率为万分之一，默认值）；
     3：严格(误识率为十万分之一）；
     4：非常严格(误识率为百万分之一）；**/
    private Integer security_level;
    /**表示云端判断为假脸后，是否依然进行比对；
     0: 默认值，云端判断为假脸，则直接返回结果，不执行比对，可以节省成本；
     1: 云端判断为假脸后，依然进行比对**/
    private Integer force_compare;
    /**对于image_ref1,image_ref2 参考照片，当检测不出人脸时，是否旋转90度、180度、270度后再检测人脸。
     "1": 默认值，要旋转检测；
     “0”：不旋转；
     请注意：设置此参数为1可能会轻微增加误检人脸的概率，如果您明确您的业务场景里不存在非正向的人脸图片、或概率极低，建议勿设置此参数**/
    private Integer multi_oriented_detection;

    public BizTokenModel() {
        super();
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

    public String getLiveness_type() {
        return liveness_type;
    }

    public void setLiveness_type(String liveness_type) {
        this.liveness_type = liveness_type;
    }

    public Integer getComparison_type() {
        return comparison_type;
    }

    public void setComparison_type(Integer comparison_type) {
        this.comparison_type = comparison_type;
    }

    public String getIdcard_name() {
        return idcard_name;
    }

    public void setIdcard_name(String idcard_name) {
        this.idcard_name = idcard_name;
    }

    public String getIdcard_number() {
        return idcard_number;
    }

    public void setIdcard_number(String idcard_number) {
        this.idcard_number = idcard_number;
    }

    public File getImage_ref1() {
        return image_ref1;
    }

    public void setImage_ref1(File image_ref1) {
        this.image_ref1 = image_ref1;
    }

    public File getImage_ref2() {
        return image_ref2;
    }

    public void setImage_ref2(File image_ref2) {
        this.image_ref2 = image_ref2;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBiz_no() {
        return biz_no;
    }

    public void setBiz_no(String biz_no) {
        this.biz_no = biz_no;
    }

    public Integer getLiveness_timeout() {
        return liveness_timeout;
    }

    public void setLiveness_timeout(Integer liveness_timeout) {
        this.liveness_timeout = liveness_timeout;
    }

    public Integer getLiveness_action_count() {
        return liveness_action_count;
    }

    public void setLiveness_action_count(Integer liveness_action_count) {
        this.liveness_action_count = liveness_action_count;
    }

    public Integer getSecurity_level() {
        return security_level;
    }

    public void setSecurity_level(Integer security_level) {
        this.security_level = security_level;
    }

    public Integer getForce_compare() {
        return force_compare;
    }

    public void setForce_compare(Integer force_compare) {
        this.force_compare = force_compare;
    }

    public Integer getMulti_oriented_detection() {
        return multi_oriented_detection;
    }

    public void setMulti_oriented_detection(Integer multi_oriented_detection) {
        this.multi_oriented_detection = multi_oriented_detection;
    }
}
