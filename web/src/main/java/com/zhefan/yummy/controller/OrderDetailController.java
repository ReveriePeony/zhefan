package com.zhefan.yummy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.OrderDetail;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.service.OrderDetailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 订单详情 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "订单详情")
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController extends BaseController {
	

	@Autowired
	private OrderDetailService orderDetailService;
	
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<List<OrderDetail>> list(@ApiParam("订单ID") Integer orderId) {
		if (orderId == null) return ResponseDTO.error(ResponseEnums.ORDER_ID_NULL);
		Wrapper<OrderDetail> wrapper = new EntityWrapper<>();
		wrapper.eq("order_id", orderId);
		return ResponseDTO.success(orderDetailService.selectList(wrapper));
	}
	
}
