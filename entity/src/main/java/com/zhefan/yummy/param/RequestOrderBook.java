package com.zhefan.yummy.param;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年12月25日
 *
 */
@Api("下单对象")
@Data
public class RequestOrderBook{

	@ApiModelProperty(value = "商家(后台)账号ID", required = true)
	@NotNull(message = "商家(后台)账号ID不能为空")
	private Integer gerentId;

	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	@NotNull(message = "商家(后台)店铺ID不能为空")
	private Integer shopId;
	
	@ApiModelProperty(value = "价格()", required = true)
	@NotNull(message = "价格不能为空")
	private Double price;

	@ApiModelProperty("区域")
	private String areaName;

	@ApiModelProperty(value = "桌号", required = true)
	@NotBlank(message = "桌号不能为空")
	private String tableName;
	
	@ApiModelProperty(value = "下单者名称", required = true)
	private String book;

	@ApiModelProperty(value = "下单者ID", required = true)
	private Integer bookId;
	
	@ApiModelProperty(value = "详细")
	private List<RequestOrderDetailBook> details;

}
