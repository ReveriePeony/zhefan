package com.zhefan.yummy.mobileController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.param.RequestDishesList;
import com.zhefan.yummy.service.DishesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	@PostMapping("list")
	public ResponseDTO<Page<Dishes>> list(@RequestBody RequestDishesList desh) {
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
	
}
