package com.zhefan.yummy.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜品
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("菜品")
@Data
public class RequestDishes {

	private Integer id;
    /**
     * 商家(后台)账号ID
     */
	@ApiModelProperty(value = "商家(后台)账号ID", required = true)
	@NotNull(message = "gerentId不能为空")
	private Integer gerentId;
    /**
     * 商家(后台)店铺ID
     */
	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	@NotNull(message = "shopId不能为空")
	private Integer shopId;
    /**
     * 菜品类型ID
     */
	@ApiModelProperty(value = "菜品类型ID", required = true)
	@NotNull(message = "dishesClassId不能为空")
	private Integer dishesClassId;
    /**
     * 菜品名称
     */
	@ApiModelProperty(value = "菜品名称", required = true)
	@NotBlank(message = "dishesName不能为空")
	private String dishesName;
    /**
     * 菜品图片
     */
	@ApiModelProperty(value = "菜品图片")
	private String dishesImg;
    /**
     * 序号
     */
	@ApiModelProperty(value = "序号")
	private Integer serialNo;
    /**
     * 描述
     */
	@ApiModelProperty(value = "描述")
	private String remark;
	/**
	 * 菜价格(分)
	 */
	@ApiModelProperty(value = "菜价格(分)", required = true)
	@NotNull(message = "dishesPrice不能为空")
	private Integer dishesPrice;
}
