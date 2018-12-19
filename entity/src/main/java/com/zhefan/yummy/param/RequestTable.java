package com.zhefan.yummy.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

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
public class RequestTable {

	private Integer id;
    /**
     * 商家(后台)店铺ID
     */
	@NotNull(message = "shopId不能为空")
	@ApiModelProperty(value = "商家(后台)店铺ID", required = true)
	private Integer shopId;
    /**
     * 餐桌类型ID
     */
	@NotNull(message = "tableTypeId不能为空")
	@ApiModelProperty(value = "餐桌类型ID", required = true)
	private Integer tableTypeId;
    /**
     * 区域ID
     */
	@ApiModelProperty(value = "区域ID")
	private Integer areaId;
    /**
     * 餐桌名称(桌号)
     */
	@NotBlank(message = "tableName不能为空")
	@ApiModelProperty(value = "餐桌名称(桌号)", required = true)
	private String tableName;
    /**
     * 描述
     */
	private String remark;

}
