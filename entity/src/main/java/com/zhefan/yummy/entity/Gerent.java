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
 * 商家(后台)账号
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("商家(后台)账号")
@Data
@Accessors(chain = true)
@TableName("t_gerent")
public class Gerent extends Model<Gerent> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	@ApiModelProperty("名称")
	private String name;
    /**
     * 头像
     */
	@ApiModelProperty("头像")
	private String avatar;
    /**
     * 密码
     */
	@ApiModelProperty("密码")
	private String password;
    /**
     * 创建时间
     */
	private String creationTime;
    /**
     * 手机号
     */
	@ApiModelProperty("手机号")
	private String tel;
    /**
     * 创建者名称
     */
	@ApiModelProperty("创建者名称")
	private String creator;
    /**
     * 创建者ID
     */
	private Integer creatorId;
    /**
     * 状态 0-注销 1-正常
     */
	@ApiModelProperty("状态 0-注销 1-正常")
	private Integer status;
    /**
     * 角色ID
     */
	@ApiModelProperty("角色ID")
	private Integer roleId;
    /**
     * 角色名
     */
	@ApiModelProperty("角色名")
	private String roleName;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
