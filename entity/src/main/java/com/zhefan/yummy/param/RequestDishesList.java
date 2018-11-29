package com.zhefan.yummy.param;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * <p>
 * 菜品List
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("菜品List")
@Data
public class RequestDishesList extends ParamPage{

	private static final long serialVersionUID = -2819172618238908781L;

	@ApiModelProperty(value = "菜品ID")
	private Integer dishesId;

	@NotNull(message = "shopId不能为空")
	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	private Integer shopId;

	@ApiModelProperty(value = "菜品类型ID")
	private Integer dishesClassId;

	@ApiModelProperty(value = "菜品名称")
	private String dishesName;
	
	@ApiModelProperty(value = "使用状态 0-下架 1-上架")
	private Integer status;

	@ApiModelProperty(value = "是否售罄 0-false 1-true")
	private Integer soldOut;
}
