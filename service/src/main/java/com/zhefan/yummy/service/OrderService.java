package com.zhefan.yummy.service;

import java.util.List;

import com.zhefan.yummy.base.BaseService;
import com.zhefan.yummy.entity.Order;
import com.zhefan.yummy.param.RequestOrderBook;
import com.zhefan.yummy.vo.OrderVO;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface OrderService extends BaseService<Order> {

	/**
	 * 保存
	 * @param bo
	 * @param currentTime
	 * @return
	 */
	boolean save(RequestOrderBook bo, String currentTime);

	List<OrderVO> queryMobileListAll(Integer shopId, String tableName, String keyword);
	
}
