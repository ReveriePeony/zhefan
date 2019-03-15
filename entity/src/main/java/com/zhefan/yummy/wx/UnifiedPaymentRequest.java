package com.zhefan.yummy.wx;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信统一下单参数
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年3月14日
 *
 */
@Api("微信统一下单参数")
@Data
public class UnifiedPaymentRequest {

	/**
	 * (*)商品描述  String(128) 商品简单描述，该字段请按照规范传递，具体请见参数规定
	 */
	@ApiModelProperty(value = "商品描述  String(128) 商品简单描述，该字段请按照规范传递，具体请见参数规定", required = true)
	@NotBlank(message = "商品描述必填")
	private String body;
	
	/**
	 * 附加数据	attach	否	String(127)	深圳分店	附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
	 */
	@ApiModelProperty(value = "附加数据	attach	否	String(127)	深圳分店	附加数据，在查询API和支付通知中原样返回")
	private String attach;
	
	/**
	 * (*)标价金额	total_fee	是	Int	88	订单总金额，单位为分，详见支付金额
	 */
	@ApiModelProperty(value = "标价金额	total_fee	是	Int	88	订单总金额，单位为分，详见支付金额")
	@NotBlank(message = "标价金额必填")
	private String total_fee;
	
}
