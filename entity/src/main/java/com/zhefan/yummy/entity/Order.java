package com.zhefan.yummy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

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
@Data
@Accessors(chain = true)
@TableName("t_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家(后台)账号ID
     */
	private Integer gerentId;
    /**
     * 商家(后台)店铺ID
     */
	private Integer shopId;
    /**
     * 订单号
     */
	private String orderNumber;
    /**
     * 价格
     */
	private Double price;
    /**
     * (下单)创建时间
     */
	private String creationTime;
    /**
     * 状态时间
     */
	private String processTime;
    /**
     * 下单者名称
     */
	private String book;
    /**
     * 下单者ID
     */
	private Integer bookId;
    /**
     * 使用状态 0-待取餐 1-已取消 2-已完成
     */
	private Integer status;
    /**
     * 区域
     */
	private String areaName;
    /**
     * 桌号
     */
	private String tableName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
