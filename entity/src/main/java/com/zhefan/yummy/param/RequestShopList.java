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
public class RequestShopList extends ParamPage {

	private static final long serialVersionUID = -7665193696542239389L;

	private Integer id;
	/**
	 * 店铺名称
	 */
	@ApiModelProperty("店铺名称")
	private String shopName;
	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
	private String address;
	/**
	 * 创建时间
	 */
	private String creationTime;
	/**
	 * 创建者名称
	 */
	private String creator;
	/**
	 * 使用状态 0-注销 1-正常
	 */
	@ApiModelProperty("使用状态 0-注销 1-正常")
	private String status;
	/**
	 * 店铺手机
	 */
	@ApiModelProperty("店铺手机正常")
	private String shopTel;

}
