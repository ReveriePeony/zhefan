package com.zhefan.yummy.service.impl;

import com.zhefan.yummy.entity.User;
import com.zhefan.yummy.dao.UserDAO;
import com.zhefan.yummy.service.UserService;
import com.zhefan.yummy.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-10-30
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDAO, User> implements UserService {
	
}
