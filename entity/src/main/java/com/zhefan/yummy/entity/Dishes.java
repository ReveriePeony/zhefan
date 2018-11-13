package com.zhefan.yummy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜品
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("菜品")
@Data
@Accessors(chain = true)
@TableName("t_dishes")
public class Dishes extends Model<Dishes> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家(后台)账号ID
     */
	@ApiModelProperty(value = "商家(后台)账号ID")
	private Integer gerentId;
    /**
     * 商家(后台)店铺ID
     */
	@ApiModelProperty(value = "商家(后台)店铺ID")
	private Integer shopId;
    /**
     * 菜品类型ID
     */
	@ApiModelProperty(value = "菜品类型ID")
	private Integer dishesClassId;
    /**
     * 菜品名称
     */
	@ApiModelProperty(value = "菜品名称")
	private String dishesName;
    /**
     * 菜品图片
     */
	@ApiModelProperty(value = "菜品图片")
	private String dishesImg;
    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
	private String creationTime;
    /**
     * 创建者名称
     */
	@ApiModelProperty(value = "创建者名称")
	private String creator;
    /**
     * 创建者ID
     */
	private Integer creatorId;
    /**
     * 使用状态 0-下架 1-上架
     */
	@ApiModelProperty(value = "使用状态 0-下架 1-上架")
	private Integer status = 1;
    /**
     * 是否售罄 0-false 1-true
     */
	@ApiModelProperty(value = "是否售罄 0-false 1-true")
	private Integer soldOut = 0;
    /**
     * 序号
     */
	@ApiModelProperty(value = "序号")
	private Integer serialNo;
    /**
     * 描述
     */
	@ApiModelProperty(value = "描述")
	private String remark;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
