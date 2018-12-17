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
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.Role;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestRole;
import com.zhefan.yummy.param.RequestRoleList;
import com.zhefan.yummy.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api(tags = "菜品")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "列表", notes = "列表")
	@GetMapping("list")
	public ResponseDTO<Page<Role>> list(RequestRoleList role) {
		Page<Role> page = role.initPage();
		Wrapper<Role> wrapper = new EntityWrapper<>();
		if (role.getId() != null)
			wrapper.eq("id", role.getId());
		if (role.getRoleName() != null)
			wrapper.like("role_name", role.getRoleName());
		roleService.selectPage(page, wrapper);
		return ResponseDTO.success(page);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@Valid @RequestBody RequestRole role, BindingResult result, HttpServletRequest request) {
		Gerent gerent = getGerent(request);
		Role entity = new Role();
		BeanUtils.copyProperties(role, entity);
		if (role.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
			entity.setCreator(gerent.getNick());
		}
		boolean b = roleService.insertOrUpdate(entity);
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
		boolean b = roleService.delRole(ids);
		if (!b)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		return ResponseDTO.success();
	}

}
