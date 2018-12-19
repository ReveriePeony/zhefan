package com.zhefan.yummy.param;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年12月19日
 *
 */
@Api("登录账号")
@Data
public class RequestLogin {

	/**
	 * 名称
	 */
	@NotBlank(message = "名称(账号)不能为空")
	@ApiModelProperty(value = "名称(账号)", required = true)
	private String name;
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	@ApiModelProperty(value = "密码", required = true)
	private String password;

}
