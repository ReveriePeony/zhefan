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
 * 订单详情
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Data
@Accessors(chain = true)
@TableName("t_order_detail")
public class OrderDetail extends Model<OrderDetail> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 订单ID
     */
	private Integer orderId;
    /**
     * 数量
     */
	private Integer count;
    /**
     * 单价
     */
	private Double price;
    /**
     * 菜品类型名称
     */
	private String dishesTypeName;
    /**
     * 菜品名称
     */
	private String dishesName;
    /**
     * (下单)创建时间
     */
	private String creationTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
