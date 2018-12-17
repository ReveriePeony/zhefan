package com.zhefan.yummy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestDishes;
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
@Api(tags = "菜品")
@RestController
@RequestMapping("/dishes")
public class DishesController extends BaseController {

	@Autowired
	private DishesService dishesService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${file.url.change}")
	private String changeUrl;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<Page<Dishes>> list(RequestDishesList desh) {
		if (desh.getShopId() == null)
			return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		Page<Dishes> page = desh.initPage();
		Wrapper<Dishes> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", desh.getShopId());
		if (desh.getDishesId() != null)
			wrapper.eq("id", desh.getDishesId());
		if (desh.getDishesClassId() != null)
			wrapper.eq("dishes_class_id", desh.getDishesClassId());
		if (desh.getStatus() != null)
			wrapper.eq("status", desh.getStatus());
		if (desh.getSoldOut() != null)
			wrapper.eq("sold_out", desh.getSoldOut());
		wrapper.andNew().eq("1", "1");
		if (desh.getDishesName() != null)
			wrapper.or().like("dishes_name", desh.getDishesName());
		dishesService.selectPage(page, wrapper);
		return ResponseDTO.success(page);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@Valid @RequestBody RequestDishes clas, BindingResult result, HttpServletRequest request) {
		Gerent gerent = getGerent(request);
		Dishes entity = new Dishes();
		BeanUtils.copyProperties(clas, entity);
		String dishesImg = entity.getDishesImg().replace("temp/", "");

		LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
		param.add("startFilePath", entity.getDishesImg());
		param.add("endFilePath", dishesImg);
		param.add("token", getFileProjectToken());
		restTemplate.postForObject(changeUrl, param, JSONObject.class);

		entity.setDishesImg(dishesImg);
		if (clas.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
			entity.setCreator(gerent.getNick());
		}
		boolean b = dishesService.insertOrUpdate(entity);
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
		boolean b = dishesService.deleteBatchIds(ids);
		if (!b)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		return ResponseDTO.success();
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "上架", notes = "上架")
	@PostMapping("sale")
	public ResponseDTO sales(@RequestBody List<Integer> ids) {
		if (ids == null || ids.size() == 0)
			return ResponseDTO.error(ResponseEnums.ID_NULL);
		Wrapper<Dishes> wrapper = new EntityWrapper<>();
		wrapper.in("id", ids);
		Dishes entity = new Dishes();
		entity.setSoldOut(Dishes.STATUS_UP);
		boolean b = dishesService.update(entity, wrapper);
		if (!b)
			return ResponseDTO.error(ResponseEnums.OPERATING_ERROR);
		return ResponseDTO.success();
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "下架", notes = "下架")
	@PostMapping("notSale")
	public ResponseDTO notSale(@RequestBody List<Integer> ids) {
		if (ids == null || ids.size() == 0)
			return ResponseDTO.error(ResponseEnums.ID_NULL);
		Wrapper<Dishes> wrapper = new EntityWrapper<>();
		wrapper.in("id", ids);
		Dishes entity = new Dishes();
		entity.setSoldOut(Dishes.STATUS_DOWN);
		boolean b = dishesService.update(entity, wrapper);
		if (!b)
			return ResponseDTO.error(ResponseEnums.OPERATING_ERROR);
		return ResponseDTO.success();
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "售罄", notes = "售罄")
	@PostMapping("soldOut")
	public ResponseDTO soldOut(@RequestBody List<Integer> ids) {
		if (ids == null || ids.size() == 0)
			return ResponseDTO.error(ResponseEnums.ID_NULL);
		Wrapper<Dishes> wrapper = new EntityWrapper<>();
		wrapper.in("id", ids);
		Dishes entity = new Dishes();
		entity.setSoldOut(Dishes.SOLD_OUT);
		boolean b = dishesService.update(entity, wrapper);
		if (!b)
			return ResponseDTO.error(ResponseEnums.OPERATING_ERROR);
		return ResponseDTO.success();
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "有货", notes = "有货")
	@PostMapping("sold")
	public ResponseDTO sold(@RequestBody List<Integer> ids) {
		if (ids == null || ids.size() == 0)
			return ResponseDTO.error(ResponseEnums.ID_NULL);
		Wrapper<Dishes> wrapper = new EntityWrapper<>();
		wrapper.in("id", ids);
		Dishes entity = new Dishes();
		entity.setSoldOut(Dishes.SOLD_IN);
		boolean b = dishesService.update(entity, wrapper);
		if (!b)
			return ResponseDTO.error(ResponseEnums.OPERATING_ERROR);
		return ResponseDTO.success();
	}

}
