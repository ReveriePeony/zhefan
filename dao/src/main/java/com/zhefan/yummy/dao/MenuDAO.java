package com.zhefan.yummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhefan.yummy.entity.Menu;

/**
 * <p>
  * 菜单(URL) Mapper 接口
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface MenuDAO extends BaseMapper<Menu> {

	
	List<Menu> selectMenuList(@Param("roleId") Integer roleId);

}