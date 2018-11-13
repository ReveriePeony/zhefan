package com.zhefan.yummy.param;

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
	@ApiModelProperty(value = "商家(后台)账号ID")
	private Integer gerentId;
    /**
     * 商家(后台)店铺ID
     */
	@ApiModelProperty(value = "商家(后台)店铺ID")
	private Integer shopId;
    /**
     * 菜品类型ID
     */
	@ApiModelProperty(value = "菜品类型ID")
	private Integer dishesClassId;
    /**
     * 菜品名称
     */
	@ApiModelProperty(value = "菜品名称")
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

}
