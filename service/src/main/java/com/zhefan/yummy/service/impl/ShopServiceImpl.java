package com.zhefan.yummy.service.impl;

import java.text.SimpleDateFormat;
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
import com.zhefan.yummy.dao.ShopDAO;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.Shop;
import com.zhefan.yummy.param.RequestShop;
import com.zhefan.yummy.service.ShopService;

/**
 * <p>
 * 店铺 服务实现类
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<ShopDAO, Shop> implements ShopService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${file.url.change}")
	private String changeUrl;

	@Value("${file.url.del}")
	private String delUrl;

	@Override
	public boolean save(RequestShop shop, Gerent gerent, String fileToken) {
		Shop entity = new Shop();
		BeanUtils.copyProperties(shop, entity);
		String[] shopImg = entity.getShopImg().split(",");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < shopImg.length; i++) {
			String replaceImg = shopImg[i].replace("temp/", "");
			LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
			param.add("startFilePath", shopImg[i]);
			param.add("endFilePath", replaceImg);
			param.add("token", fileToken);
			restTemplate.postForObject(changeUrl, param, JSONObject.class);
			sb.append(replaceImg);
			if (i < shopImg.length - 1)
				sb.append(",");
		}
		entity.setShopImg(sb.toString());
		if (shop.getId() == null) {
			entity.setGerentId(gerent.getId());
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
			entity.setCreator(gerent.getNick());
		} else {
			Wrapper<Shop> w = new EntityWrapper<>();
			w.eq("id", shop.getId());
			Shop selectOne = selectOne(w);
			String shopImg2 = selectOne.getShopImg();
			if (shopImg2 != null) {
				String[] split = shopImg2.split(",");
				List<String> asList = Arrays.asList(shopImg);
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

}
