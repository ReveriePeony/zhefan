package com.zhefan.yummy.service;

import java.util.List;

import com.zhefan.yummy.base.BaseService;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.param.RequestDishes;
import com.zhefan.yummy.vo.DishesClassVO;
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

	/**
	 * 
	 * @param clas
	 * @param gerent 登录者
	 * @param fileToken
	 * @return
	 */
	boolean save(RequestDishes clas, Gerent gerent, Object fileToken);

	/**
	 * 全菜品 mobile
	 * @param shopId
	 * @param keyword
	 * @return
	 */
	List<DishesClassVO> queryAllDishesForClass(Integer shopId, String keyword);

	/**
	 * 全菜品 mobile
	 * @param shopId
	 * @param keyword
	 * @return
	 */
	List<DishesClassVO> selectAllForMobile(Integer shopId, String keyword);
	
}
