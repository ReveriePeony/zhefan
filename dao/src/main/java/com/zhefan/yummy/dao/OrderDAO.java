package com.zhefan.yummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhefan.yummy.entity.Order;
import com.zhefan.yummy.vo.OrderVO;

/**
 * <p>
  * 订单 Mapper 接口
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface OrderDAO extends BaseMapper<Order> {

	List<OrderVO> queryMobileListAll(@Param("shopId") Integer shopId, @Param("bookId") Integer bookId,
			@Param("keyword") String keyword);

}