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
 * 区域
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("区域")
@Data
@Accessors(chain = true)
@TableName("t_area")
public class Area extends Model<Area> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 商家(后台)店铺ID
	 */
	@ApiModelProperty(value = "商家(后台)店铺ID")
	private Integer shopId;
	/**
	 * 区域名称
	 */
	@ApiModelProperty(value = "区域名称")
	private String areaName;
	/**
	 * 创建者ID
	 */
	@ApiModelProperty(value = "创建者ID")
	private Integer creatorId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private String creationTime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
