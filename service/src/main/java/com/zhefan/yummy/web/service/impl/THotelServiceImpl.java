package com.zhefan.yummy.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseServiceImpl;
import com.zhefan.yummy.dao.THotelDAO;
import com.zhefan.yummy.entity.THotel;
import com.zhefan.yummy.param.HotelParameter.HotelQuery;
import com.zhefan.yummy.vo.HotelVo;
import com.zhefan.yummy.web.service.THotelService;

/**
 * <p>
 * 酒店主表 服务实现类
 * </p>
 *
 * @author
 * @since 2017-10-09
 */
@Service
public class THotelServiceImpl extends BaseServiceImpl<THotelDAO, THotel> implements THotelService {

	@Autowired
	THotelDAO tHotelDAO;

	@SuppressWarnings("unchecked")
	@Override
	public Page<HotelVo> queryHotelHome(Integer busid, HotelQuery hpage) {
		Page<HotelVo> page = hpage.initPage();
		page.setRecords(tHotelDAO.queryHotelHome(page, hpage, busid));
		return page;
	}

}
