package com.zhefan.yummy.vo;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜品类型VO
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年1月16日
 *
 */
@Api("菜品类型")
@Data
public class DishesClassVO {

	@ApiModelProperty("ID")
	private Integer dishesClassId;
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
	 * 创建时间
	 */
	private String creationTime;
	/**
	 * 创建者名称
	 */
	private String creator;
	/**
	 * 使用状态 0-注销 1-正常
	 */
	@ApiModelProperty("使用状态 0-注销 1-正常")
	private Integer status;
	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	private String remark;
	
	@ApiModelProperty("菜品集合")
	private List<DishesVo> dishes;

}
