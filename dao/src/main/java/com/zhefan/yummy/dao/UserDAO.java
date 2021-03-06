package com.zhefan.yummy.dao;

import com.zhefan.yummy.entity.User;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 用户 Mapper 接口
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface UserDAO extends BaseMapper<User> {
	
	int updateUserByOpenId(@Param("user") User user);

}