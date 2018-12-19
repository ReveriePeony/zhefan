package com.zhefan.yummy.param;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜品类型List
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("菜品类型List")
@Data
public class RequestDishesClassList extends ParamPage{

	@NotNull(message = "shopId不能为空")
	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	private Integer shopId;

	@ApiModelProperty(value = "菜品类型ID")
	private Integer classId;

	@ApiModelProperty(value = "菜品类型名称")
	private String className;
	
}
