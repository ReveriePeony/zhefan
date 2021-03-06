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
 * 店铺
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("店铺")
@Data
@Accessors(chain = true)
@TableName("t_shop")
public class Shop extends Model<Shop> {

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
	 * 店铺名称
	 */
	@ApiModelProperty("店铺名称")
	private String shopName;
	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
	private String address;
	/**
	 * 经度
	 */
	@ApiModelProperty("经度")
	private Double longitude;
	/**
	 * 纬度
	 */
	@ApiModelProperty("纬度")
	private Double latitude;
	/**
	 * 图片
	 */
	@ApiModelProperty("图片")
	private String shopImg;
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
	private String remark;
	/**
	 * 店铺手机
	 */
	private String shopTel;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
