package com.zhefan.yummy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Shop;
import com.zhefan.yummy.param.ParamPage;
import com.zhefan.yummy.service.ShopService;

/**
 * <p>
 * 店铺 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-10-30
 */
@RestController
@RequestMapping("/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;

	@GetMapping("findAll/{gerentId}")
	public ResponseDTO<Page<Shop>> login(@PathVariable("gerentId") Integer gerentId, ParamPage paramPage) {
		Wrapper<Shop> wrapper = new EntityWrapper<>();
		wrapper.eq("gerent_id", gerentId);
		Page<Shop> page = shopService.selectPage(paramPage.initPage(), wrapper);
		return ResponseDTO.createSuccess(page);
	}

}
