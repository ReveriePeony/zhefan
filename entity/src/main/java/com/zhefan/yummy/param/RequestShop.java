package com.zhefan.yummy.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class RequestShop {

	private Integer id;
	/**
	 * 店铺名称
	 */
	@ApiModelProperty(value = "店铺名称", required = true)
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
	 * 序号
	 */
	@ApiModelProperty("序号")
	private Integer serialNo;
	/**
	 * 描述
	 */
	private String remark;

}
