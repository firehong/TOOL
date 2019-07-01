package com.cgmcomm.utils.faceUtil.model;

/**
 * @auther Macro
 * @date 2019/6/18 10:11
 * @Description  旷视查询结果错误码
 */
public enum  FaceErrorCode {
    SUCCESS(1000,"待比对照片与第三方权威数据照片比对结果是同一个人"),

    PASS_LIVING_NOT_THE_SAME(2000,"待比对照片与第三方权威数据照片比对结果不是同一个人"),

    NO_ID_CARD_NUMBER(3000,"第三方权威数据无此身份证号"),
    ID_NUMBER_NAME_NOT_MATCH(3000,"身份证号，姓名不匹配"),
    NO_FACE_FOUND(3000,"第三方权威数据照片中找不到人脸"),
    NO_ID_PHOTO(3000,"无法获取第三方权威数据照片"),
    PHOTO_FORMAT_ERROR(3000,"第三方权威数据照片格式错误"),
    DATA_SOURCE_ERROR(3000,"其他第三方权威数据照片错误"),

    FAIL_LIVING_FACE_ATTACK(4100,"未经过活体判断，可能的原因：是假脸攻击"),
    REPLACED_FACE_ATTACK(4100,"发生换脸攻击，在做活体过程中出现两张不相同的人脸"),

    BIZ_TOKEN_DENIED(4200,"传入的 biz_token 不符合要求"),
    AUTHENTICATION_FAIL(4200,"鉴权失败"),
    MOBILE_PHONE_NOT_SUPPORT(4200,"手机在不支持列表里"),
    SDK_TOO_OLD(4200,"SDK版本过旧，已经不被支持"),
    MOBILE_PHONE_NO_AUTHORITY(4200,"没有权限（运动传感器、存储、相机）"),
    USER_CANCELLATION(4200,"用户活体失败，可能原因：用户取消了"),
    USER_TIMEOUT(4200,"用户活体失败，可能原因：验证过程超时"),
    VERIFICATION_FAILURE(4200,"用户活体失败，可能原因：验证失败"),
    UNDETECTED_FACE(4200,"用户活体失败，可能原因：未检测到人脸"),
    ACTION_ERROR(4200,"用户活体失败，可能原因：用户动作错误"),

    API_KEY_BE_DISCONTINUED(5000,"api_key被停用"),
    IP_NOT_ALLOWED(5000,"不允许访问的IP"),
    NON_ENTERPRISE_CERTIFICATION(5000,"客户未进行企业认证"),
    BALANCE_NOT_ENOUGH(5000,"余额不足"),
    MORE_RETRY_TIMES(5000,"获取服务器配置时超过重试次数"),
    ACCOUNT_DISCONTINUED(5000,"用户帐号已停用"),
    EXPIRED_SIGN(5000,"签名过期"),
    INVALID_SIGN(5000,"无效的签名"),
    REPLAY_ATTACK(5000,"重放攻击，单次有效的签名被多次使用"),

    USER_CANCEL(6000,"用户取消"),
    NO_CAMERA_PERMISSION(6000,"没有打开相机的权限，请开启权限后重试"),
    ILLEGAL_PARAMETER(6000,"传入参数不合法"),
    DEVICE_NOT_SUPPORT(6000,"无法启动相机，请确认摄像头功能完好"),
    INVALID_BUNDLE_ID(6000,"信息验证失败，请重启程序或设备后重试"),
    NETWORK_ERROR(6000,"连不上互联网，请连接上互联网后重试"),
    FACE_INIT_FAIL(6000,"无法启动人脸识别，请稍后重试"),
    LIVENESS_DETECT_FAILED(6000,"活体检测不通过"),
    NO_SENSOR_PERMISSION(6000,"无法读取运动数据的权限，请开启权限后重试"),
    INIT_FAILED(6000,"初始化失败"),

    LIVING_NOT_START(9000,"活体验证没有开始"),
    LIVING_IN_PROGRESS(9000,"正在进行验证"),
    LIVING_OVERTIME(9000,"操作超时，由于用户在长时间没有进行操作"),

    ;

    /**错误码**/
    private Integer code;
    /**错误描述**/
    private String desc;

    FaceErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
