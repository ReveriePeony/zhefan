package com.zhefan.yummy.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.zhefan.yummy.constant.CommonSessionConst;
import com.zhefan.yummy.exception.ResponseEntityException;
import com.zhefan.yummy.util.RedisCacheUtil;

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
	 * 获取Sessionid
	 *
	 * @param session HttpSession
	 * @return
	 */
	public String getSessionId(HttpSession session) {
		return session.getId();
	}

	/**
	 *
	 * @param session HttpSession
	 * @return int
	 */
	public Integer getLoginUserId(HttpSession session) {
		Object o = session.getAttribute("");
		return 33;
	}

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

//	protected String authorizeMember(HttpServletRequest request, Map<String, Object> map) throws Exception {
//		
//	}

	/**
	 * 判断浏览器
	 *
	 * @return 1:微信浏览器,0:其他浏览器
	 */
	public static Integer judgeBrowser(HttpServletRequest request) {
		Integer result = null;
		String ua = request.getHeader("user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {
			result = 1;	//weixin
		} else {
			result = 0;
		}
		return result;
	}

	public static String getHost(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	}
}
