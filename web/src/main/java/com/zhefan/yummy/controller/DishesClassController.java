package com.zhefan.yummy.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.DishesClass;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.param.RequestDishesClass;
import com.zhefan.yummy.service.DishesClassService;
import com.zhefan.yummy.util.SessionUtil;

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
@Api(tags = "菜品类型")
@RestController
@RequestMapping("/dishesClass")
public class DishesClassController extends BaseController {
	
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
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@RequestBody RequestDishesClass clas, HttpServletRequest request) {
		Gerent gerent = SessionUtil.getLoginBean(request);
		DishesClass entity = new DishesClass();
		BeanUtils.copyProperties(clas, entity);
		if(clas.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
			entity.setCreator(gerent.getNick());
		}
		boolean b = dishesClassService.insertOrUpdate(entity);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids) {
		boolean b = dishesClassService.deleteBatchIds(ids);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
	
}
