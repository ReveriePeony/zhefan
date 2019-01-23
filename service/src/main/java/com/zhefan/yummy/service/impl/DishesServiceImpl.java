package com.zhefan.yummy.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseServiceImpl;
import com.zhefan.yummy.dao.DishesDAO;
import com.zhefan.yummy.entity.Dishes;
import com.zhefan.yummy.entity.DishesClass;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.param.RequestDishes;
import com.zhefan.yummy.service.DishesClassService;
import com.zhefan.yummy.service.DishesService;
import com.zhefan.yummy.vo.DishesClassVO;
import com.zhefan.yummy.vo.DishesVo;

/**
 * <p>
 * 菜品 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Service
public class DishesServiceImpl extends BaseServiceImpl<DishesDAO, Dishes> implements DishesService {

	@Autowired
	private DishesDAO dishesDAO;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DishesClassService dishesClassService;

	@Value("${file.url.change}")
	private String changeUrl;

	@Value("${file.url.del}")
	private String delUrl;
	
	@Override
	public List<DishesVo> selectPageFull(Integer shopId, String keyword) {
		return dishesDAO.selectPageFull(shopId, "%" + keyword + "%");
	}

	@Override
	public boolean save(RequestDishes clas, Gerent gerent, Object fileToken) {
		Dishes entity = new Dishes();
		BeanUtils.copyProperties(clas, entity);
		String[] dishesImg = entity.getDishesImg().split(",");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dishesImg.length; i++) {
			String replaceImg = dishesImg[i].replace("temp/", "");
			LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
			param.add("startFilePath", dishesImg[i]);
			param.add("endFilePath", replaceImg);
			param.add("token", fileToken);
			restTemplate.postForObject(changeUrl, param, JSONObject.class);
			sb.append(replaceImg);
			if (i < dishesImg.length - 1)
				sb.append(",");
		}
		entity.setDishesImg(sb.toString());
		if (clas.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
			entity.setCreator(gerent.getNick());
		}else {
			Wrapper<Dishes> w = new EntityWrapper<>();
			w.eq("id", clas.getId());
			Dishes selectOne = selectOne(w);
			String dishesImg2 = selectOne.getDishesImg();
			if (dishesImg2 != null) {
				String[] split = dishesImg2.split(",");
				List<String> asList = Arrays.asList(dishesImg);
				for (int i = 0; i < split.length; i++) {
					if (asList.contains(split[i])) {
						LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
						param.add("id", gerent.getId());
						param.add("img", split[i]);
						param.add("token", fileToken);
						restTemplate.postForObject(delUrl, param, JSONObject.class);
					}
				}
			}
		}
		return insertOrUpdate(entity);
	}

	@Override
	public List<DishesClassVO> queryAllDishesForClass(Integer shopId, String keyword) {
		Wrapper<DishesClass> classWrapper = new EntityWrapper<>();
		classWrapper.eq("shop_id", shopId);
		classWrapper.andNew().eq("1", "1");
		if(keyword != null) classWrapper.or().like("dishes_class_name", keyword);
		List<DishesClass> classes = dishesClassService.selectList(classWrapper);
		
		Wrapper<Dishes> wrapper = new EntityWrapper<>();
		wrapper.eq("shop_id", shopId);
		wrapper.andNew().eq("1", "1");
		if(keyword != null) wrapper.or().like("dishes_name", keyword);
		List<Dishes> dishes = selectList(wrapper);
		
		List<DishesClassVO> classVOs = new ArrayList<>();
		for(DishesClass dc : classes) {
			DishesClassVO classVO = new DishesClassVO();
			BeanUtils.copyProperties(dc, classVO);
			List<DishesVo> dishesVos = new ArrayList<>();
			for(Dishes d : dishes) {
				if(d.getDishesClassId().equals(dc.getId())) {
					DishesVo dishesVo = new DishesVo();
					BeanUtils.copyProperties(d, dishesVo);
					dishesVos.add(dishesVo);
				}
				classVO.setDishes(dishesVos);
			}
			classVOs.add(classVO);
		}
		return classVOs;
	}

	@Override
	public List<DishesClassVO> selectAllForMobile(Integer shopId, String keyword) {
		keyword = keyword == null ? "" : keyword; 
		return dishesDAO.selectAllForMobile(shopId, "%" + keyword + "%");
	}

}
