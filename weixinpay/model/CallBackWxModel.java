package com.cgmcomm.webapi.utils.weixinpay.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信回调实体
 * 
 * @author wuhong  2017年11月27日 下午2:00:18 
 * @version share 1.0
 */
@Data
public class CallBackWxModel implements Serializable{

	private static final long serialVersionUID = -5424613434748268634L;

	private String return_code;
	private String return_msg;

}
