package com.zhefan.yummy.param;

import java.util.List;

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
	private Integer gerentId;

	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	private Integer shopId;
	
	@ApiModelProperty(value = "价格(分)", required = true)
	private String price;

	@ApiModelProperty("区域")
	private String areaName;

	@ApiModelProperty(value = "桌号", required = true)
	private String tableName;
	
	@ApiModelProperty(value = "下单者名称", required = true)
	private String book;

	@ApiModelProperty(value = "下单者ID", required = true)
	private Integer bookId;
	
	@ApiModelProperty(value = "详细", required = true)
	private List<RequestOrderDetailBook> details;

}
