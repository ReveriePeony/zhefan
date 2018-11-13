package com.zhefan.yummy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhefan.yummy.base.BaseServiceImpl;
import com.zhefan.yummy.dao.MenuDAO;
import com.zhefan.yummy.entity.Menu;
import com.zhefan.yummy.service.MenuService;

/**
 * <p>
 * 菜单(URL) 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDAO, Menu> implements MenuService {

	@Autowired
	private MenuDAO menuDAO;
	
	@Override
	public List<Menu> selectMenuList(Integer roleId) {
		return menuDAO.selectMenuList(roleId);
	}
	
}
