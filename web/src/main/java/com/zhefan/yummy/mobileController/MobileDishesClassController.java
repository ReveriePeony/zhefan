package com.zhefan.yummy.mobileController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.DishesClass;
import com.zhefan.yummy.service.DishesClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
	@PostMapping("list")
	public ResponseDTO<List<DishesClass>> list(@ApiParam("商店ID") Integer shopId, 
			@ApiParam("菜品类型ID") Integer classId, @ApiParam("类型名") Integer className) {
		Wrapper<DishesClass> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", shopId);
		if(classId != null) wrapper.eq("id", classId);
		if(className != null) wrapper.eq("dishes_class_name", className);
		return ResponseDTO.success(dishesClassService.selectList(wrapper));
	}
	
}
