package com.zhefan.yummy.base;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.ResponseEntityException;
import com.zhefan.yummy.util.RedisCacheUtil;
import com.zhefan.yummy.util.SessionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Reverien9@gmail.com
 */
@Slf4j
public abstract class BaseController {

	@Autowired
	RedisCacheUtil redisCacheUtil;

	/**
	 * 参数校验
	 *
	 * @param result BindingResult
	 */
	protected void InvalidParameter(BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			for (ObjectError error : errorList) {
				log.warn(error.getDefaultMessage());
				throw new ResponseEntityException(ResponseEnums.SAVE_ERROR.getCode(), error.getDefaultMessage());
			}
		}
	}

	protected String getCurrentTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
	}

	protected Gerent getGerent(HttpServletRequest request) {
//		String token = request.getHeader("token");
//		if (token == null) {
//			throw new BaseException(ResponseEnums.NEED_LOGIN, "/login");
//		}
//		Object object = redisCacheUtil.get(token);
//		if (object == null) {
//			throw new BaseException(ResponseEnums.JWT_TOKEN_EXPIRED, "/login");
//		}
//		return JSONObject.parseObject(object.toString(), Gerent.class);
		return SessionUtil.getLoginBean(request);
	}

	protected String getRealPath(String path, HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/WEB-INF/classes/" + path);
	}

	protected String getResourcePath(Object obj) {
		return obj.getClass().getResource("/").getPath();
	}

	protected String checkBindingResult(BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			for (ObjectError error : errorList) {
				return error.getDefaultMessage();
			}
		}
		return null;
	}

	protected String getFileProjectToken() {
		return DigestUtils
				.md5Hex("fi" + new SimpleDateFormat("yyyyMMddHHmm").format(System.currentTimeMillis()) + "le");
	}

	/**
	 * 判断浏览器
	 *
	 * @return 1:微信浏览器,0:其他浏览器
	 */
	public Integer judgeBrowser(HttpServletRequest request) {
		Integer result = null;
		String ua = request.getHeader("user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {
			result = 1; // weixin
		} else {
			result = 0;
		}
		return result;
	}

	public String getHost(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	}
}
