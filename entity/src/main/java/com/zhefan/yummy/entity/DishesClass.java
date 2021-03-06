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
 * 菜品类型
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("菜品类型")
@Data
@Accessors(chain = true)
@TableName("t_dishes_class")
public class DishesClass extends Model<DishesClass> {

	private static final long serialVersionUID = 1L;
	
	public static final int STATUS_UP = 1;

	public static final int STATUS_DOWN = 0;

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
	 * 创建者ID
	 */
	private Integer creatorId;
	/**
	 * 使用状态 0-注销 1-正常
	 */
	@ApiModelProperty("使用状态 0-注销 1-正常")
	private Integer status = STATUS_UP;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
