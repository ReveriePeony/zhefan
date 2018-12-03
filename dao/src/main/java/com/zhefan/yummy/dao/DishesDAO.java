package com.zhefan.yummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.vo.DishesVo;

/**
 * <p>
  * 菜品 Mapper 接口
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface DishesDAO extends BaseMapper<Dishes> {

	List<DishesVo> selectPageFull(@Param("shopId") Integer shopId, @Param("keyword") String keyword);

}