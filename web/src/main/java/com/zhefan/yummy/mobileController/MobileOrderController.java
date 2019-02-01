package com.zhefan.yummy.mobileController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestOrderBook;
import com.zhefan.yummy.service.OrderService;
import com.zhefan.yummy.vo.OrderVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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

	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("listAll")
	public ResponseDTO<List<OrderVO>> listAll(@ApiParam("商铺ID") Integer shopId, @ApiParam("用户ID") Integer bookId, 
			@ApiParam("关键字") String keyword) {
		if (shopId == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		if (bookId == null) return ResponseDTO.error(ResponseEnums.USER_ID_IS_NULL);
		return ResponseDTO.success(orderService.queryMobileListAll(shopId, bookId, keyword));
	}
	
}
