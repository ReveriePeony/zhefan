package com.zhefan.yummy.base;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.zhefan.yummy.entity.Gerent;
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
				throw new ResponseEntityException(error.getDefaultMessage());
			}
		}
	}
	
	protected String getCurrentTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
	}
	
	protected Gerent getGerent(HttpServletRequest request) {
		return SessionUtil.getLoginBean(request);
	}
	
	protected String getRealPath(String path, HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(path);
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
	
	/**
	 * 判断浏览器
	 *
	 * @return 1:微信浏览器,0:其他浏览器
	 */
	public Integer judgeBrowser(HttpServletRequest request) {
		Integer result = null;
		String ua = request.getHeader("user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {
			result = 1;	//weixin
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
