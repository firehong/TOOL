package com.cgmcomm.webapi.utils.weixinpay.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * H5需要参数实体
 * 
 * @author Macro  2017年11月29日 下午10:11:11 
 * @version share 1.0
 */
@Data
@NoArgsConstructor
public class PayH5Model {
	
	private String appId;

    private String timeStamp;

    private String nonceStr;

    private String packAge;

    private String signType;

    private String paySign;
    
    private String return_code;
    
	private String result_code;
    
    private String return_msg;

    public PayH5Model(String result_code, String return_msg) {
        this.result_code = result_code;
        this.return_msg = return_msg;
    }
}
