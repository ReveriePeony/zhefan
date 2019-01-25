package com.zhefan.yummy.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单详情VO
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年1月25日
 *
 */
@Api("订单详情")
@Data
public class OrderDetailVO {

	private Integer id;
    /**
     * 订单ID
     */
	@ApiModelProperty("订单ID")
	private Integer orderId;
    /**
     * 数量
     */
	@ApiModelProperty("数量")
	private Integer count;
    /**
     * 单价
     */
	@ApiModelProperty("单价()")
	private Double price;
    /**
     * 菜品类型名称
     */
	@ApiModelProperty("菜品类型名称")
	private String dishesTypeName;
    /**
     * 菜品名称
     */
	@ApiModelProperty("菜品名称")
	private String dishesName;
    /**
     * (下单)创建时间
     */
	private String creationTime;

}
