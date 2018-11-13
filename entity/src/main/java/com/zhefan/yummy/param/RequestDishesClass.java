package com.zhefan.yummy.param;

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
	@ApiModelProperty("商家(后台)账号ID")
	private Integer gerentId;
	/**
	 * 商家(后台)店铺ID
	 */
	@ApiModelProperty("商家(后台)店铺ID")
	private Integer shopId;
	/**
	 * 菜品类型名称
	 */
	@ApiModelProperty("菜品类型名称")
	private String dishesClassName;
	/**
	 * 序号
	 */
	@ApiModelProperty("序号")
	private Integer serialNo;
	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	private String remark;

}
