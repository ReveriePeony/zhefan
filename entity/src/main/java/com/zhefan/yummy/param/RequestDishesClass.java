package com.zhefan.yummy.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜品类型
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("菜品类型")
@Data
public class RequestDishesClass {

	private Integer id;
	/**
	 * 商家(后台)账号ID
	 */
	@NotNull(message = "gerentId不能为空")
	@ApiModelProperty("商家(后台)账号ID")
	private Integer gerentId;
	/**
	 * 商家(后台)店铺ID
	 */
	@NotNull(message = "shopId不能为空")
	@ApiModelProperty("商家(后台)店铺ID")
	private Integer shopId;
	/**
	 * 菜品类型名称
	 */
	@NotBlank(message = "dishesClassName不能为空")
	@ApiModelProperty("菜品类型名称")
	private String dishesClassName;
	/**
	 * 序号
	 */
	@ApiModelProperty("序号")
	private Integer serialNo = 0;
	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	private String remark;

}
