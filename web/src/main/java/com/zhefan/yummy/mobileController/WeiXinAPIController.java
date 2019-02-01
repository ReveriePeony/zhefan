package com.zhefan.yummy.mobileController;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.User;
import com.zhefan.yummy.service.UserService;
import com.zhefan.yummy.util.WXUtils;
import com.zhefan.yummy.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 微信小程序接口
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年1月30日
 *
 */
@Api(tags = "微信小程序接口")
@RestController
@RequestMapping("/mobile/wx")
public class WeiXinAPIController extends BaseController {
	
	@Autowired
	private WXUtils wxUtils;
	
	@Autowired
	private UserService userService;
	
	@Value("${wx.appid}")
	private String appid;

	@Value("${wx.appsecrret}")
	private String appsecrret;
	
	@ApiOperation(value = "微信登录", notes = "微信登录")
	@GetMapping("auth")
	public ResponseDTO<UserVO> auth(@ApiParam("code") String code, @ApiParam("iv") String iv,
			@ApiParam("encryptedData") String encryptedData) {
		JSONObject miniJson = wxUtils.getMiniLoginInfo(appid, appsecrret, code);
		if(!"0".equals(miniJson.getString("errcode"))) {
			return ResponseDTO.error(miniJson.getIntValue("errcode"), miniJson.getString("errmsg"));
		}
		String sessionkey = miniJson.getString("session_key");
		JSONObject userInfo = wxUtils.getDecryptUserInfo(encryptedData, sessionkey, iv);
		UserVO user = new UserVO();
		if(userInfo != null) {
			Wrapper<User> wrapper = new EntityWrapper<>();
			wrapper.eq("openid", userInfo.getString("openId"));
			User one = userService.selectOne(wrapper);
			if(one == null) {
				User two = new User();
				two.copyUserInfo(userInfo);
				userService.insert(two);
				BeanUtils.copyProperties(two, user);
			} else {
				BeanUtils.copyProperties(one, user);
			}
		}
		return ResponseDTO.success(user);
	}
	
	@ApiOperation(value = "微信登录(测试-只返回测试号", notes = "微信登录(测试-只返回测试号")
	@GetMapping("autht")
	public ResponseDTO<UserVO> autht(@ApiParam("code") String code, @ApiParam("iv") String iv,
			@ApiParam("encryptedData") String encryptedData) {
		JSONObject miniJson = wxUtils.getMiniLoginInfo(appid, appsecrret, code);
		if(!"0".equals(miniJson.getString("errcode"))) {
			return ResponseDTO.error(miniJson.getIntValue("errcode"), miniJson.getString("errmsg"));
		}
		String sessionkey = miniJson.getString("session_key");
		JSONObject userInfo = wxUtils.getDecryptUserInfo(encryptedData, sessionkey, iv);
		System.err.println("wx userInfo = ============================== = " + userInfo);
		UserVO user = new UserVO();
		Wrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("id", 1000);
		User one = userService.selectOne(wrapper);
		BeanUtils.copyProperties(one, user);
		return ResponseDTO.success(user);
	}
	
	
	
	
	
	
	
	
	
	
}
