package com.zhefan.yummy.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class RequestArea {

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

}
