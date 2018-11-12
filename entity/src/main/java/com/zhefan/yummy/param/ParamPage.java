package com.zhefan.yummy.param;

import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月29日
 *
 */
@Api("Page参数")
@SuppressWarnings("rawtypes")
@Data
public class ParamPage extends Page{
	
	private static final long serialVersionUID = 4953570822783975651L;

	@ApiModelProperty("页数")
	private Integer page = 1;
	
	@ApiModelProperty("行数")
	private Integer pageSize = 10;
	
	@ApiModelProperty("排序")
	private String orderByField;
	
	public Page initPage() {
		return new Page(page, pageSize, orderByField);
	}
}
