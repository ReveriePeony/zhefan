package com.zhefan.yummy.mobileController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.DishesClass;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestDishesClassList;
import com.zhefan.yummy.service.DishesClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 菜品类型 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "m - 菜品类型")
@RestController
@RequestMapping("/mobile/dishesClass")
public class MobileDishesClassController extends BaseController {
	
	@Autowired
	private DishesClassService dishesClassService;
	
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<List<DishesClass>> list(RequestDishesClassList classList) {
		if (classList.getShopId() == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		Wrapper<DishesClass> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", classList.getShopId()).eq("status", DishesClass.STATUS_UP);
		if(classList.getClassId() != null) wrapper.eq("id", classList.getClassId());
		if(classList.getClassName() != null) wrapper.eq("dishes_class_name", classList.getClassName());
		return ResponseDTO.success(dishesClassService.selectList(wrapper));
	}
	
}
