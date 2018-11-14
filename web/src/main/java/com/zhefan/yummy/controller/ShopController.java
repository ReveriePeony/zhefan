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
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.Shop;
import com.zhefan.yummy.param.RequestShop;
import com.zhefan.yummy.param.RequestShopList;
import com.zhefan.yummy.service.ShopService;
import com.zhefan.yummy.util.SessionUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 店铺 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "店铺")
@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {
	
	@Autowired
	private ShopService shopService;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "列表", notes = "列表")
	@PostMapping("list")
	public ResponseDTO<Page<Shop>> list(@RequestBody RequestShopList shop, HttpServletRequest request) {
		Gerent gerent = getGerent(request);
		Page<Shop> page = shop.initPage();
		Wrapper<Shop> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Shop.STATUS_UP).eq("gerent_id", gerent.getId());
		if(shop.getId() != null) wrapper.eq("id", shop.getId());
		wrapper.andNew().eq("1", "1");
		if(shop.getShopName() != null) wrapper.or().like("shop_name", shop.getShopName());
		if(shop.getAddress() != null) wrapper.or().like("price", shop.getAddress());
		if(shop.getCreationTime() != null) wrapper.like("creation_time", shop.getCreationTime());
		if(shop.getStatus() != null) wrapper.like("status", shop.getStatus());
		if(shop.getRemark() != null) wrapper.like("remark", shop.getRemark());
		shopService.selectPage(page, wrapper);
		return ResponseDTO.success(page);
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@RequestBody RequestShop shop, HttpServletRequest request) {
		Gerent gerent = SessionUtil.getLoginBean(request);
		Shop entity = new Shop();
		BeanUtils.copyProperties(shop, entity);
		if(shop.getId() == null) {
			entity.setGerentId(gerent.getId());
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
			entity.setCreator(gerent.getNick());
		}
		boolean b = shopService.insertOrUpdate(entity);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids) {
		Wrapper<Shop> wrapper = new EntityWrapper<>();
		wrapper.in("id", ids);
		Shop entity = new Shop();
		entity.setStatus(Shop.STATUS_DOWN);
		boolean b = shopService.update(entity, wrapper);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
}
