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
import com.zhefan.yummy.entity.Area;
import com.zhefan.yummy.param.RequestTable;
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
	@PostMapping("list")
	public ResponseDTO<List<Area>> list(@ApiParam("商店ID") Integer shopId, 
			@ApiParam("区域ID") Integer areaId) {
		Wrapper<Area> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", shopId);
		if(areaId != null) wrapper.eq("id", areaId);
		return ResponseDTO.success(areaService.selectList(wrapper));
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("save")
	public ResponseDTO save(@RequestBody RequestTable table, HttpServletRequest request) {
//		Gerent gerent = SessionUtil.getLoginBean(request);
		Area entity = new Area();
		System.err.println(table);
		BeanUtils.copyProperties(table, entity);
		System.err.println(entity);
		boolean b = areaService.insertOrUpdate(entity);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("del")
	public ResponseDTO list(@RequestBody List<Integer> ids) {
		System.err.println(ids);
		boolean b = areaService.deleteBatchIds(ids);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
}
