package com.zhefan.yummy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhefan.yummy.base.BaseServiceImpl;
import com.zhefan.yummy.dao.DishesDAO;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.service.DishesService;
import com.zhefan.yummy.vo.DishesVo;

/**
 * <p>
 * 菜品 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Service
public class DishesServiceImpl extends BaseServiceImpl<DishesDAO, Dishes> implements DishesService {

	@Autowired
	private DishesDAO dishesDAO;
	
	@Override
	public List<DishesVo> selectPageFull(Integer shopId, String keyword) {
		return dishesDAO.selectPageFull(shopId, keyword);
	}
	
}
