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
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.param.RequestDishes;
import com.zhefan.yummy.param.RequestDishesList;
import com.zhefan.yummy.service.DishesService;
import com.zhefan.yummy.util.SessionUtil;

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
@Api(tags = "菜品")
@RestController
@RequestMapping("/dishes")
public class DishesController extends BaseController {
	
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
		if(desh.getDishesName() != null) wrapper.eq("dishes_name", desh.getDishesName());
		if(desh.getStatus() != null) wrapper.eq("status", desh.getStatus());
		if(desh.getSoldOut() != null) wrapper.eq("sold_out", desh.getSoldOut());
		dishesService.selectPage(page, wrapper);
		return ResponseDTO.success(page);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@RequestBody RequestDishes clas, HttpServletRequest request) {
		Gerent gerent = SessionUtil.getLoginBean(request);
		Dishes entity = new Dishes();
		BeanUtils.copyProperties(clas, entity);
		entity.setCreatorId(gerent.getId());
		entity.setCreationTime(getCurrentTime());
		entity.setCreator(gerent.getName());
		boolean b = dishesService.insertOrUpdate(entity);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("del")
	public ResponseDTO list(@RequestBody List<Integer> ids) {
		boolean b = dishesService.deleteBatchIds(ids);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
}
