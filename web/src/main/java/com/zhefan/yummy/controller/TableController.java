package com.zhefan.yummy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.Table;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestTable;
import com.zhefan.yummy.param.RequestTableList;
import com.zhefan.yummy.service.TableService;
import com.zhefan.yummy.vo.TableVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 餐桌 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "餐桌")
@RestController
@RequestMapping("/table")
public class TableController extends BaseController {

	@Autowired
	private TableService tableService;

	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<Page<TableVo>> list(RequestTableList table) {
		if(table.getShopId() == null) return ResponseDTO.error(ResponseEnums.SHOP_ID_NULL);
		if (StringUtils.isNotBlank(table.getTableName()))
			table.setTableName("%" + table.getTableName() + "%");
		return ResponseDTO.success(tableService.selectPageVo(table));
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@Valid @RequestBody RequestTable table, BindingResult result, HttpServletRequest request) {
		InvalidParameter(result);
		Gerent gerent = getGerent(request);
		Table entity = new Table();
		BeanUtils.copyProperties(table, entity);
		if (table.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
		}
		boolean b = tableService.insertOrUpdate(entity);
		if (!b)
			return ResponseDTO.error(ResponseEnums.SAVE_ERROR);
		return ResponseDTO.success();
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids) {
		if(ids == null || ids.size() == 0) return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		boolean b = tableService.deleteBatchIds(ids);
		if (!b)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		return ResponseDTO.success();
	}

}
