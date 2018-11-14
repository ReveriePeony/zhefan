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
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.TableType;
import com.zhefan.yummy.param.RequestTableType;
import com.zhefan.yummy.service.TableTypeService;
import com.zhefan.yummy.util.SessionUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 餐桌类型 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "餐桌类型")
@RestController
@RequestMapping("/tableType")
public class TableTypeController extends BaseController {
	
	@Autowired
	private TableTypeService tableTypeService;
	
	@ApiOperation(value = "列表", notes = "列表")
	@PostMapping("list")
	public ResponseDTO<List<TableType>> list(@ApiParam("商店ID") Integer shopId, 
			@ApiParam("ID") Integer id, @ApiParam("名") Integer tableName) {
		Wrapper<TableType> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", shopId);
		if(id != null) wrapper.eq("id", id);
		if(tableName != null) wrapper.eq("table_name", tableName);
		return ResponseDTO.success(tableTypeService.selectList(wrapper));
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@RequestBody RequestTableType tableType, HttpServletRequest request) {
		Gerent gerent = SessionUtil.getLoginBean(request);
		TableType entity = new TableType();
		BeanUtils.copyProperties(tableType, entity);
		if(tableType.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
		}
		boolean b = tableTypeService.insertOrUpdate(entity);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids) {
		boolean b = tableTypeService.deleteBatchIds(ids);
		if(!b) return ResponseDTO.error();
		return ResponseDTO.success();
	}
	
}
