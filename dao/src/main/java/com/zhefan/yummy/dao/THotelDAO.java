package com.zhefan.yummy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zhefan.yummy.entity.THotel;
import com.zhefan.yummy.param.HotelParameter;
import com.zhefan.yummy.vo.HotelVo;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月29日
 *
 */
public interface THotelDAO extends BaseMapper<THotel> {

    List<HotelVo> queryHotelHome(@Param("page") Pagination page, @Param("req") HotelParameter.HotelQuery hpage, @Param("busId") Integer busid);

}