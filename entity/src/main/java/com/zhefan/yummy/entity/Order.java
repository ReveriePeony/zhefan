package com.zhefan.yummy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("t_order")
public class Order extends Model<Order> {
	
	public static final int BEING = 1;
	
	public static final int NON_BEING = 0;
	
	public static final int STATUS_WAIT = 0;
	
	public static final int STATUS_CANCEL = 1;
	
	public static final int STATUS_WEND = 2;

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
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
	@ApiModelProperty("价格(分)")
	private Integer price;
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
	private Integer status = 0;
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
	
	private Integer being = 1;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
