package com.zhefan.yummy.mobileController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestDishesList;
import com.zhefan.yummy.service.DishesService;
import com.zhefan.yummy.vo.DishesVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 菜品 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "m - 菜品")
@RestController
@RequestMapping("/mobile/dishes")
public class MobileDishesController extends BaseController {
	
	@Autowired
	private DishesService dishesService;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<Page<Dishes>> list(RequestDishesList desh) {
		if (desh.getShopId() == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		Page<Dishes> page = desh.initPage();
		Wrapper<Dishes> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", desh.getShopId());
		if(desh.getDishesId() != null) wrapper.eq("id", desh.getDishesId());
		if(desh.getDishesClassId() != null) wrapper.eq("dishes_class_id", desh.getDishesClassId());
		if(desh.getStatus() != null) wrapper.eq("status", desh.getStatus());
		if(desh.getSoldOut() != null) wrapper.eq("sold_out", desh.getSoldOut());
		wrapper.andNew().eq("1", "1");
		if(desh.getDishesName() != null) wrapper.or().like("dishes_name", desh.getDishesName());
		dishesService.selectPage(page, wrapper);
		return ResponseDTO.success(page);
	}
	
	@ApiOperation(value = "列表 Full", notes = "列表 Full")
	@GetMapping("listFull")
	public ResponseDTO<List<DishesVo>> listFull(@ApiParam("商铺ID") Integer shopId, @ApiParam("关键字") String keyword) {
		if (shopId == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		return ResponseDTO.success(dishesService.selectPageFull(shopId, keyword));
	}
	
}
