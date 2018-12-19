package com.zhefan.yummy.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Order;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestOrderList;
import com.zhefan.yummy.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
	
	@Autowired
	private OrderService orderService;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<Page<Order>> list(RequestOrderList order, HttpServletRequest request) {
		if (order.getShopId() == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		Integer gerentId = order.getGerentId();
		if(gerentId == null) gerentId = getGerent(request).getId();
		Page<Order> page = order.initPage();
		Wrapper<Order> wrapper = new EntityWrapper<>();
		wrapper.eq("gerent_id", gerentId).eq("shop_id", order.getShopId()).eq("being", Order.BEING);
		if(order.getOrderId() != null) wrapper.eq("id", order.getOrderId());
		wrapper.andNew().eq("1", "1");
		if(StringUtils.isNotBlank(order.getOrderNumber())) wrapper.or().like("order_number", order.getOrderNumber());
		if(order.getPrice() != null) wrapper.or().like("price", order.getPrice());
		if(order.getCreationTime() != null) wrapper.like("creation_time", order.getCreationTime());
		if(order.getProcessTime() != null) wrapper.like("process_time", order.getProcessTime());
		if(order.getBook() != null) wrapper.like("book", order.getBook());
		if(order.getStatus() != null) wrapper.like("status", order.getStatus());
		if(StringUtils.isNotBlank(order.getAreaName())) wrapper.like("area_name", order.getAreaName());
		if(StringUtils.isNotBlank(order.getTableName())) wrapper.like("table_name", order.getTableName());
		orderService.selectPage(page, wrapper);
		return ResponseDTO.success(page);
	}
	
//	@SuppressWarnings("rawtypes")
//	@ApiOperation(value = "保存", notes = "保存")
//	@PostMapping("save")
//	public ResponseDTO save(@RequestBody RequestDishesClass clas, HttpServletRequest request) {
//		Gerent gerent = getGerent(request);
//		DishesClass entity = new DishesClass();
//		BeanUtils.copyProperties(clas, entity);
//		if(clas.getId() == null) {
//			entity.setCreatorId(gerent.getId());
//			entity.setCreationTime(getCurrentTime());
//			entity.setCreator(gerent.getNick());
//		}
//		boolean b = dishesClassService.insertOrUpdate(entity);
//		if(!b) return ResponseDTO.error();
//		return ResponseDTO.success();
//	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids) {
		if (ids == null || ids.size() == 0)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		Wrapper<Order> wrapper = new EntityWrapper<>();
		wrapper.in("id", ids);
		Order entity = new Order();
		entity.setBeing(Order.NON_BEING);
		boolean b = orderService.update(entity, wrapper);
		if(!b) return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		return ResponseDTO.success();
	}
	
}
