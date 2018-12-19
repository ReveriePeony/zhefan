package com.zhefan.yummy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhefan.yummy.base.BaseService;
import com.zhefan.yummy.entity.Table;
import com.zhefan.yummy.param.RequestTableList;
import com.zhefan.yummy.vo.TableVo;

/**
 * <p>
 * 餐桌 服务类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
public interface TableService extends BaseService<Table> {

	Page<TableVo> selectPageVo(RequestTableList table);
	
}
