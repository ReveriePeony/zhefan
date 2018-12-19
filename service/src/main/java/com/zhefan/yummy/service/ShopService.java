package com.zhefan.yummy.service;

import com.zhefan.yummy.base.BaseService;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.Shop;
import com.zhefan.yummy.param.RequestShop;

/**
 * <p>
 * 店铺 服务类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface ShopService extends BaseService<Shop> {

	/**
	 * 
	 * @param shop 
	 * @param gerent
	 * @return
	 */
	boolean save(RequestShop shop, Gerent gerent, String fileToken);
	
}
