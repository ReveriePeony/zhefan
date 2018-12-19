package com.zhefan.yummy.vo;

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
public class TableVo {


	private Integer id;
	/**
	 * 商家(后台)店铺ID
	 */
	@ApiModelProperty("商家(后台)店铺ID")
	private Integer shopId;
	/**
	 * 餐桌类型ID
	 */
	@ApiModelProperty("餐桌类型ID")
	private Integer tableTypeId;
	/**
	 * 区域ID
	 */
	@ApiModelProperty("区域ID")
	private Integer areaId;
	/**
	 * 餐桌名称(桌号)
	 */
	@ApiModelProperty("餐桌名称(桌号)")
	private String tableName;
	/**
	 * 创建者ID
	 */
	private Integer creatorId;
	/**
	 * 创建时间
	 */
	private String creationTime;
	/**
	 * 描述
	 */
	private String remark;

	/**
     * 餐桌类型名称
     */
	@ApiModelProperty("餐桌类型名称")
	private String tableTypeName;
}
