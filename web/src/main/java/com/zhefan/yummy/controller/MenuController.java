package com.zhefan.yummy.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Menu;
import com.zhefan.yummy.service.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 菜单(URL) 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "菜单(URL)")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Autowired
	private MenuService menuService;
	
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<List<Menu>> list(@ApiParam(value = "角色ID") Integer roleId, 
			HttpServletRequest request) {
		if(roleId == null) roleId = getGerent(request).getRoleId();
		return ResponseDTO.success(menuService.selectMenuList(roleId));
	}
	
//	@SuppressWarnings("rawtypes")
//	@ApiOperation(value = "删除", notes = "删除")
//	@PostMapping("del")
//	public ResponseDTO del(@RequestBody List<Integer> ids, HttpServletRequest request) {
//		Gerent gerent = getGerent(request);
//		Menu menu2 = menuService.selectById(ids.get(0));
//		if(!"admin".equals(gerent.getName()) && menu2.getCreatorId().equals(gerent.getId())) {
//			return ResponseDTO.error("只有管理员和创建本人可操作");
//		}
//		boolean b = menuService.deleteBatchIds(ids);
//		if(!b) return ResponseDTO.error();
//		return ResponseDTO.success();
//	}
	
}
