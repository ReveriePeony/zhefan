package com.zhefan.yummy.vo;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单VO
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年1月25日
 *
 */
@Api("订单")
@Data
public class OrderVO {
	
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
	 * 订单号
	 */
	@ApiModelProperty("订单号")
	private String orderNumber;
	/**
	 * 价格
	 */
	@ApiModelProperty("价格()")
	private Double price;
	/**
	 * (下单)创建时间
	 */
	private String creationTime;
	/**
	 * 状态时间
	 */
	@ApiModelProperty("状态时间")
	private String processTime;
	/**
	 * 下单者名称
	 */
	@ApiModelProperty("下单者名称")
	private String book;
	/**
	 * 下单者ID
	 */
	private Integer bookId;
	/**
	 * 使用状态 0-待取餐 1-已取消 2-已完成
	 */
	@ApiModelProperty("使用状态 0-待取餐 1-已取消 2-已完成")
	private Integer status;
	/**
	 * 区域
	 */
	@ApiModelProperty("区域")
	private String areaName;
	/**
	 * 桌号
	 */
	@ApiModelProperty("桌号")
	private String tableName;
	
	private Integer being;

	@ApiModelProperty("详情")
	private List<OrderDetailVO> orderDetails;
}
