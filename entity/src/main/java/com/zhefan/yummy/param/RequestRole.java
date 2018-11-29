package com.zhefan.yummy.param;

import org.hibernate.validator.constraints.NotBlank;

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
public class RequestRole {

	private Integer id;
	/**
	 * 角色名
	 */
	@NotBlank(message = "roleName不能为空")
	@ApiModelProperty("角色名")
	private String roleName;

}
