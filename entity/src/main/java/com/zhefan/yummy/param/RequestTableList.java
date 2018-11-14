package com.zhefan.yummy.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 餐桌
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("餐桌")
@Data
public class RequestTableList extends ParamPage {


	private static final long serialVersionUID = -8142276889948808709L;
	
	private Integer id;
	/**
	 * 商家(后台)店铺ID
	 */
	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	private Integer shopId;
	/**
	 * 餐桌类型ID
	 */
	@ApiModelProperty("餐桌类型ID")
	private String tableTypeId;
	/**
	 * 区域ID
	 */
	@ApiModelProperty("区域ID")
	private String areaId;
	/**
	 * 餐桌名称(桌号)
	 */
	@ApiModelProperty("餐桌名称(桌号)")
	private String tableName;
	/**
	 * 创建时间
	 */
	private String creationTime;
	/**
	 * 描述
	 */
	private String remark;

}
