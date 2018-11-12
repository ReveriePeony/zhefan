package com.zhefan.yummy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Area;
import com.zhefan.yummy.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 区域 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "区域")
@RestController
@RequestMapping("/area")
public class AreaController extends BaseController {
	
	@Autowired
	private AreaService areaService;
	
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<List<Area>> list(@ApiParam("商店ID") Integer shopId) {
		Wrapper<Area> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", shopId);
		return ResponseDTO.createSuccess(areaService.selectList(wrapper));
	}
	
}
