package com.zhefan.yummy.param;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 餐桌类型
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("餐桌类型")
@Data
public class RequestTableTypeList extends ParamPage {


	private Integer id;
	/**
	 * 商家(后台)店铺ID
	 */
	@NotNull(message = "shopId不能为空")
	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	private Integer shopId;
	/**
	 * 餐桌名称(桌号)
	 */
	@ApiModelProperty("餐桌名称(桌号)")
	private String tableName;

}
