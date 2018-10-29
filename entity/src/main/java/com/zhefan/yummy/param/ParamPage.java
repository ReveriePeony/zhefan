package com.zhefan.yummy.param;

import com.baomidou.mybatisplus.plugins.Page;

import lombok.Data;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月29日
 *
 */
@SuppressWarnings("rawtypes")
@Data
public class ParamPage extends Page{
	
	private static final long serialVersionUID = 4953570822783975651L;

	private Integer page;
	
	private Integer pageSize;
	
	private String orderByField;
	
	public Page initPage() {
		return new Page<>(page, pageSize, orderByField);
	}
}
