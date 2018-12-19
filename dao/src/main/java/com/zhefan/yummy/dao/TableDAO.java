package com.zhefan.yummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhefan.yummy.entity.Table;
import com.zhefan.yummy.param.RequestTableList;
import com.zhefan.yummy.vo.TableVo;

/**
 * <p>
 * 餐桌 Mapper 接口
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface TableDAO extends BaseMapper<Table> {

	List<TableVo> selectPageVo(@Param("table") RequestTableList table, @Param("page") Pagination page);

}