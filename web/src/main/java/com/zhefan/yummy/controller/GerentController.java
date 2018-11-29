package com.zhefan.yummy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.param.RequestGerent;
import com.zhefan.yummy.service.GerentService;
import com.zhefan.yummy.util.SessionUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 商家(后台)账号 前端控制器
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-10-30
 */
@Api(tags = "后台账号")
@Slf4j
@RestController
@RequestMapping("/gerent")
public class GerentController extends BaseController {

	@Autowired
	private GerentService gerentService;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${file.url.del}")
	private String delUrl;
	
	@Value("${file.url.change}")
	private String changeUrl;

	@ApiOperation(value = "登录", notes = "登录")
	@SuppressWarnings("rawtypes")
	@PostMapping("login")
	public ResponseDTO login(@ApiParam("账号") @RequestParam String name, @ApiParam("密码") @RequestParam String password,
			HttpServletRequest request) {
		if (name == null)
			ResponseDTO.error(ResponseEnums.NAME_ID_NULL);
		if (password == null)
			ResponseDTO.error(ResponseEnums.PWD_ID_NULL);
		Wrapper<Gerent> wrapper = new EntityWrapper<>();
		wrapper.eq("name", name).eq("password", DigestUtils.md5Hex(password).toUpperCase());
		Gerent gerent = gerentService.selectOne(wrapper);
		if (gerent != null) {
			log.debug(gerent.toString());
			SessionUtil.setLoginInfo(request, gerent);
			LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
			param.add("id", gerent.getId());
			restTemplate.postForObject(delUrl, param, JSONObject.class);
			return ResponseDTO.success("success");
		}
		return ResponseDTO.error(ResponseEnums.LOGIN_ERROR);
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存", notes = "保存")
	@PostMapping("save")
	public ResponseDTO save(@Valid @RequestBody RequestGerent requestGerent, BindingResult result,
			HttpServletRequest request) {
		Gerent gerent = SessionUtil.getLoginBean(request);
		Gerent entity = new Gerent();
		BeanUtils.copyProperties(requestGerent, entity);
		String aImg = entity.getAvatar().replace("temp/", "");

		LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
		param.add("startFilePath", entity.getAvatar());
		param.add("endFilePath", aImg);
		restTemplate.postForObject(changeUrl, param, JSONObject.class);
		
		if (requestGerent.getId() == null) {
			entity.setCreatorId(gerent.getId());
			entity.setCreationTime(getCurrentTime());
			entity.setCreator(gerent.getNick());
		}
		boolean b = gerentService.insertOrUpdate(entity);
		if (!b)
			return ResponseDTO.error(ResponseEnums.SAVE_ERROR);
		return ResponseDTO.success();
	}

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除", notes = "删除")
	@DeleteMapping("del")
	public ResponseDTO del(@RequestBody List<Integer> ids, HttpServletRequest request) {
		if (ids == null || ids.size() == 0)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		Gerent gerent = SessionUtil.getLoginBean(request);
		Gerent gerent2 = gerentService.selectById(ids.get(0));
		if (!"admin".equals(gerent.getName()) && gerent2.getCreatorId().equals(gerent.getId())) {
			return ResponseDTO.error("只有管理员和创建本人可操作");
		}
		boolean b = gerentService.deleteBatchIds(ids);
		if (!b)
			return ResponseDTO.error(ResponseEnums.DELETE_ERROR);
		return ResponseDTO.success();
	}

}
