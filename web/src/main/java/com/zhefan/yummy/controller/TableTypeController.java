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
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.TableType;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestTableType;
import com.zhefan.yummy.service.TableTypeService;

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
	@GetMapping("list")
	public ResponseDTO<List<TableType>> list(@ApiParam(value = "商店ID", required = true) Integer shopId, 
			@ApiParam("ID") Integer id, @ApiParam("名") Integer tableName) {
		if(shopId == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		Wrapper<TableType> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", shopId);
		if(id != null) wrapper.eq("id", id);
		if(tableName != null) wrapper.eq("table_name", tableName);
		return ResponseDTO.success(tableTypeService.selectList(wrapper));
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@Valid @RequestBody RequestTableType tableType, 
			HttpServletRequest request, BindingResult result) {
		Gerent gerent = getGerent(request);
		TableType entity = new TableType();
		BeanUtils.copyProperties(tableType, entity);
		if(tableType.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
		}
		boolean b = tableTypeService.insertOrUpdate(entity);
		if(!b) return ResponseDTO.error(ResponseEnums.SAVE_ERROR);
		return ResponseDTO.success();
	}
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids) {
		if(ids == null || ids.size() == 0) return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		boolean b = tableTypeService.deleteBatchIds(ids);
		if(!b) return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		return ResponseDTO.success();
	}
	
}
