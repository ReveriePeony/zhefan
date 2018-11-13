package com.zhefan.yummy.service;

import com.zhefan.yummy.entity.Menu;

import java.util.List;

import com.zhefan.yummy.base.BaseService;

/**
 * <p>
 * 菜单(URL) 服务类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface MenuService extends BaseService<Menu> {

	List<Menu> selectMenuList(Integer roleId);
	
	
}
