package com.zhefan.yummy.service;

import java.util.List;

import com.zhefan.yummy.base.BaseService;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.vo.DishesVo;

/**
 * <p>
 * 菜品 服务类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface DishesService extends BaseService<Dishes> {

	List<DishesVo> selectPageFull(Integer shopId, String keyword);
	
}
