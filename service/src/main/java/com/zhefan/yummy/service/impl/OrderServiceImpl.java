package com.zhefan.yummy.service.impl;

import com.zhefan.yummy.entity.Order;
import com.zhefan.yummy.dao.OrderDAO;
import com.zhefan.yummy.service.OrderService;
import com.zhefan.yummy.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderDAO, Order> implements OrderService {
	
}
