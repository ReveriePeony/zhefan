package com.zhefan.yummy.mobileController;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhefan.yummy.base.BaseController;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.entity.User;
import com.zhefan.yummy.service.UserService;
import com.zhefan.yummy.util.RedisCacheUtil;
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
	
	@Value("${wx.miniappid}")
	private String miniappid;

	@Value("${wx.appsecrret}")
	private String miniappsecrret;
	
	@Value("${wx.domain}")
	private String domain;
	
	@Autowired
	private RedisCacheUtil redisUtil;
	
	@ApiOperation(value = "微信登录(小程序)", notes = "微信登录(小程序)")
	@GetMapping("auth")
	public ResponseDTO<UserVO> auth(@ApiParam("code") String code, @ApiParam("iv") String iv,
			@ApiParam("encryptedData") String encryptedData) {
		JSONObject miniJson = wxUtils.getMiniLoginInfo(miniappid, miniappsecrret, code);
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
		JSONObject miniJson = wxUtils.getMiniLoginInfo(miniappid, miniappsecrret, code);
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
	

	@ApiOperation(value = "微信H5授权登录", notes = "微信H5授权登录")
	@GetMapping("h5auth")
	public ModelAndView h5auth(@ApiParam("url不带？（完整路径去掉协议头（http://）后使用encodeURIComponent编码）") String url) {
		ModelAndView modelAndView;
		try {
			String reUrl = URLEncoder.encode("http://" + domain + "/mobile/wx/h5authRecall", "UTF-8");
			modelAndView = new ModelAndView("redirect:/" + wxUtils.getOauth2Url(reUrl, url));
		} catch (UnsupportedEncodingException e) {
			modelAndView = new ModelAndView("404.html");
		}
		return modelAndView;
	}
	
	@ApiOperation(value = "", notes = "", hidden = true)
	@GetMapping("h5authRecall")
	public ModelAndView h5authRecall(String code, String state) {
		ModelAndView modelAndView;
		try {
			JSONObject accessTokenObject = wxUtils.getH5AccessToken(code);
			if (accessTokenObject.containsKey("errcode")) {
				modelAndView = new ModelAndView("404.html");
			} else {
				String accessToken = accessTokenObject.getString("access_token");
				String openid = accessTokenObject.getString("openid");
				JSONObject h5UserInfo = wxUtils.getH5UserInfo(accessToken, openid, null);
				Wrapper<User> wrapper = new EntityWrapper<>();
				wrapper.eq("openid", h5UserInfo.getString("openId"));
				User one = userService.selectOne(wrapper);
				UserVO user = new UserVO();
				if(one == null) {
					User two = new User();
					two.copyUserInfo(h5UserInfo);
					userService.insert(two);
					BeanUtils.copyProperties(two, user);
				} else {
					BeanUtils.copyProperties(one, user);
				}
				String token = DigestUtils.md5Hex((Math.random() * 10000) + new SimpleDateFormat("yyyyMMdd$HH").format(new Date()));
				redisUtil.set(token, JSONObject.toJSONString(user), 3600l);
				modelAndView = new ModelAndView("redirect:/" + URLDecoder.decode(state, "UTF-8") + "?token" + token);
			}
		} catch (UnsupportedEncodingException e) {
			modelAndView = new ModelAndView("404.html");
		}
		return modelAndView;
	}
	
	@ApiOperation(value = "", notes = "", hidden = true)
	@GetMapping("msg")
	public void msg(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
}
