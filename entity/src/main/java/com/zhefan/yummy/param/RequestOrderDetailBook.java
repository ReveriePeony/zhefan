package com.zhefan.yummy.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年12月25日
 *
 */
@Api("订单详情")
@Data
public class RequestOrderDetailBook {

	@ApiModelProperty(value = "数量", required = true)
	private Integer count;
	
	@ApiModelProperty(value = "单价()", required = true)
	private Double price;
	
	@ApiModelProperty(value = "菜品类型名称", required = true)
	private String dishesTypeName;
	
	@ApiModelProperty(value = "菜品名称", required = true)
	private String dishesName;
	
}
