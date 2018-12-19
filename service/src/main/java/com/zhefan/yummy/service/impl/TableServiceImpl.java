package com.zhefan.yummy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseServiceImpl;
import com.zhefan.yummy.dao.TableDAO;
import com.zhefan.yummy.entity.Table;
import com.zhefan.yummy.param.RequestTableList;
import com.zhefan.yummy.service.TableService;
import com.zhefan.yummy.vo.TableVo;

/**
 * <p>
 * 餐桌 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Service
public class TableServiceImpl extends BaseServiceImpl<TableDAO, Table> implements TableService {

	@Autowired
	private TableDAO tableDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<TableVo> selectPageVo(RequestTableList table) {
		Page<TableVo> page = table.initPage();
		page.setRecords(tableDAO.selectPageVo(table, page));
		return page;
	}
	
}
