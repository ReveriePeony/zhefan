package com.zhefan.yummy.service;

import com.zhefan.yummy.base.BaseService;
import com.zhefan.yummy.entity.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface UserService extends BaseService<User> {
	
	public boolean updateUserByOpenId(User user);
	
}
