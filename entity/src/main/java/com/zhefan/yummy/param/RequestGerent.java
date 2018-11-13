package com.zhefan.yummy.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class RequestGerent {

	private Integer id;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", required = true)
	private String name;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称", required = true)
	private String nick;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像", required = true)
	private String avatar;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号", required = true)
	private String tel;
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID", required = true)
	private Integer roleId;
	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名", required = true)
	private String roleName;

}
