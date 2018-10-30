package com.zhefan.yummy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseService;
import com.zhefan.yummy.entity.THotel;
import com.zhefan.yummy.param.HotelParameter.HotelQuery;
import com.zhefan.yummy.vo.HotelVo;

/**
 * <p>
 * 酒店主表 服务类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
public interface THotelService extends BaseService<THotel> {

	/**
	 * 查询 新增酒店 首页 酒店列表
	 *
	 * @param busid 用户ID
	 * @param page  分页对象
	 * @return 酒店列表
	 */
	Page<HotelVo> queryHotelHome(Integer busid, HotelQuery hpage);
}
