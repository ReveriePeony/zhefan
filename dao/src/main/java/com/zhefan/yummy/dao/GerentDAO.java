package com.zhefan.yummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.param.RequestParameter;

/**
 * <p>
 * 商家(后台)账号 Mapper 接口
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface GerentDAO extends BaseMapper<Gerent> {

	List<Gerent> queryHotelHome(@Param("page") Pagination page, @Param("req") RequestParameter.zQuery hpage,
			@Param("id") Integer id);

}