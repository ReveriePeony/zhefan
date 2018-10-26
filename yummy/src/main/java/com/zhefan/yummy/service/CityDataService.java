package com.zhefan.yummy.service;

import java.util.List;

import com.zhefan.yummy.model.County;

/**
 * 城市数据服务接口
 * @author Reverien9@gmail.com
 * @date 2018年4月23日
 */
public interface CityDataService {

	/**
	 * 获取城市列表
	 * @return
	 * @throws Exception
	 */
	List<County> getCityList() throws Exception;
	
}
