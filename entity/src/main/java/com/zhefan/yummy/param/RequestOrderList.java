package com.zhefan.yummy.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("订单")
@Data
public class RequestOrderList extends ParamPage{

	private static final long serialVersionUID = 6558952476433334323L;

	private Integer orderId;
	/**
	 * 商家(后台)账号ID
	 */
	@ApiModelProperty(value = "商家(后台)账号ID", required = true)
	private Integer gerentId;
	/**
	 * 商家(后台)店铺ID
	 */
	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	private Integer shopId;
	/**
	 * 订单号
	 */
	@ApiModelProperty("订单号")
	private String orderNumber;
	/**
	 * 价格
	 */
	@ApiModelProperty("价格")
	private String price;
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
	 * 使用状态 0-待取餐 1-已取消 2-已完成
	 */
	@ApiModelProperty("使用状态 0-待取餐 1-已取消 2-已完成")
	private String status;
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

}
