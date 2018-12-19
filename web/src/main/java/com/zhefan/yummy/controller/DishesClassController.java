package com.zhefan.yummy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.DishesClass;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestDishesClass;
import com.zhefan.yummy.param.RequestDishesClassList;
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
@Api(tags = "菜品类型")
@RestController
@RequestMapping("/dishesClass")
public class DishesClassController extends BaseController {

	@Autowired
	private DishesClassService dishesClassService;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<Page<DishesClass>> list(RequestDishesClassList classList) {
		if (classList.getShopId() == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		Page<DishesClass> page = classList.initPage();
		Wrapper<DishesClass> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", classList.getShopId());
		if (classList.getClassId() != null)
			wrapper.eq("id", classList.getClassId());
		if (classList.getClassName() != null)
			wrapper.eq("dishes_class_name", classList.getClassName());
		return ResponseDTO.success(dishesClassService.selectPage(page, wrapper));
	}
	
	@ApiOperation(value = "列表Full", notes = "列表Full")
	@GetMapping("listFull")
	public ResponseDTO<List<DishesClass>> listFull(@ApiParam("商店ID") Integer shopId, @ApiParam("菜品类型ID") Integer classId,
			@ApiParam("类型名") Integer className) {
		if (shopId == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		Wrapper<DishesClass> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", shopId);
		if (classId != null)
			wrapper.eq("id", classId);
		if (className != null)
			wrapper.eq("dishes_class_name", className);
		return ResponseDTO.success(dishesClassService.selectList(wrapper));
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@Valid @RequestBody RequestDishesClass clas, BindingResult result,
			HttpServletRequest request) {
		InvalidParameter(result);
		Gerent gerent = getGerent(request);
		DishesClass entity = new DishesClass();
		BeanUtils.copyProperties(clas, entity);
		if (clas.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
			entity.setCreator(gerent.getNick());
		}
		boolean b = dishesClassService.insertOrUpdate(entity);
		if (!b)
			return ResponseDTO.error(ResponseEnums.SAVE_ERROR);
		return ResponseDTO.success();
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids) {
		if (ids == null || ids.size() == 0)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		boolean b = dishesClassService.deleteBatchIds(ids);
		if (!b)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		return ResponseDTO.success();
	}

}
