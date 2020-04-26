package com.cgmcomm.webapi.utils.weixinpay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信回调实体
 * 
 * @author wuhong  2017年11月27日 下午2:00:18 
 * @version share 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallBackWxModel implements Serializable{

	private static final long serialVersionUID = -5424613434748268634L;
	/**响应code  SUCCESS:成功**/
	private String return_code;
	/**响应信息**/
	private String return_msg;

	public static CallBackWxModel success(){
		return CallBackWxModel.builder().return_code("SUCCESS").return_msg("成功").build();
	}

	public static CallBackWxModel fail(){
		return CallBackWxModel.builder().return_code("FAIL").return_msg("失败").build();
	}

}
