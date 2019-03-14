package com.zhefan.yummy.service.impl;

import com.zhefan.yummy.entity.User;
import com.zhefan.yummy.dao.UserDAO;
import com.zhefan.yummy.service.UserService;
import com.zhefan.yummy.base.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDAO, User> implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public boolean updateUserByOpenId(User user) {
		if(userDAO.updateUserByOpenId(user) != 0) return true;
		return false;
	}
	
}
