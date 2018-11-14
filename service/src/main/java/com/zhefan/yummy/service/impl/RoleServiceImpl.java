package com.zhefan.yummy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseServiceImpl;
import com.zhefan.yummy.dao.RoleDAO;
import com.zhefan.yummy.entity.Role;
import com.zhefan.yummy.entity.RoleMenu;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.ResponseEntityException;
import com.zhefan.yummy.service.RoleMenuService;
import com.zhefan.yummy.service.RoleService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Slf4j
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDAO, Role> implements RoleService {

	@Autowired
	private RoleMenuService roleMenuService;
	
	@Transactional
	@Override
	public boolean delRole(List<Integer> ids) {
		boolean deleteBatchIds = false;
		try {
			deleteBatchIds = this.deleteBatchIds(ids);
			Wrapper<RoleMenu> wrapper = new EntityWrapper<>();
			wrapper.in("role_id", ids);
			boolean delete = roleMenuService.delete(wrapper);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			throw new ResponseEntityException(ResponseEnums.DELETE_ERROR);
		}
		return deleteBatchIds;
	}
	
}
