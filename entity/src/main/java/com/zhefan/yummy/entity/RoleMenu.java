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
 * 菜单(URL)
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("菜单(URL)")
@Data
@Accessors(chain = true)
@TableName("t_role_menu")
public class RoleMenu extends Model<RoleMenu> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 角色ID
	 */
	@ApiModelProperty("角色ID")
	private Integer roleId;
	/**
	 * 菜单ID
	 */
	@ApiModelProperty("菜单ID")
	private Integer menuId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
