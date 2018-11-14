package com.zhefan.yummy.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("角色")
@Data
public class RequestRoleList extends ParamPage {

	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * 角色名
	 */
	@ApiModelProperty("角色名")
	private String roleName;

}
