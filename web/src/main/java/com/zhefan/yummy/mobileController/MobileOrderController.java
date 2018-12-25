package com.zhefan.yummy.mobileController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.param.RequestOrderBook;
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
@Api(tags = "m - 订单")
@RestController
@RequestMapping("/mobile/order")
public class MobileOrderController extends BaseController {

	@Autowired
	private OrderService orderService;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "下单", notes = "下单")
	@PostMapping("book")
	public ResponseDTO book(@Valid @RequestBody RequestOrderBook bo, BindingResult result, HttpServletRequest request) {
		InvalidParameter(result);
		orderService.save(bo, getCurrentTime());
		return ResponseDTO.success();
	}

}
