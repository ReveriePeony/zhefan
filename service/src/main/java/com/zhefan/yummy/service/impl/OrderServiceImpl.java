package com.zhefan.yummy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhefan.yummy.base.BaseServiceImpl;
import com.zhefan.yummy.dao.OrderDAO;
import com.zhefan.yummy.entity.Order;
import com.zhefan.yummy.entity.OrderDetail;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.BaseException;
import com.zhefan.yummy.param.RequestOrderBook;
import com.zhefan.yummy.service.OrderDetailService;
import com.zhefan.yummy.service.OrderService;
import com.zhefan.yummy.vo.OrderVO;

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

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Transactional
	@Override
	public boolean save(RequestOrderBook bo, String currentTime) {
		boolean flag = false;
		final Order order = new Order();
		BeanUtils.copyProperties(bo, order);
		//保留字段
//		order.setBookId(0);
//		order.setBook("暂无");
		//
		order.setOrderNumber(Order.createOrderNubmber());
		order.setCreationTime(currentTime);
		flag = insert(order);
		if(!flag) throw new BaseException(ResponseEnums.SAVE_ERROR);
		List<OrderDetail> details = new ArrayList<>();
		bo.getDetails().forEach(de -> {
			OrderDetail detail = new OrderDetail();
			BeanUtils.copyProperties(de, detail);
			detail.setOrderId(order.getId());
			detail.setCreationTime(currentTime);
			details.add(detail);
		});
		flag = orderDetailService.insertBatch(details);
		if(!flag) throw new BaseException(ResponseEnums.SAVE_ERROR);
		return flag;
	}

	@Override
	public List<OrderVO> queryMobileListAll(Integer shopId, Integer bookId, String keyword) {
		keyword = keyword == null ? "" : keyword; 
		return orderDAO.queryMobileListAll(shopId, bookId, "%" + keyword + "%");
	}
	
}
