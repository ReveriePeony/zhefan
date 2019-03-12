package com.zhefan.yummy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.entity.User;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月31日
 *
 */
public class SessionUtil {
	
	private static final String LOGIN_INFO = "login_user"; 
	private static final String MOBILE_LOGIN_INFO = "mobile_login_user"; 
	
	public static void setSessionInfo(HttpServletRequest request, String key, Object content) {
		request.getSession().setAttribute(key, JSON.toJSONString(content));
	}
	
	public static <T> T getSessionInfo(HttpServletRequest request, String key, Class<T> clazz) {
		Object sessionObj = request.getSession().getAttribute(key);
		if(sessionObj != null) {
			return JSON.parseObject(sessionObj.toString(), clazz);
		}
		return null;
	}
	
	public static void setLoginInfo(HttpServletRequest request, Object content) {
		request.getSession().setAttribute(LOGIN_INFO, JSON.toJSONString(content));
	}

	public static Gerent getLoginBean(HttpServletRequest request) {
		Object sessionObj = request.getSession().getAttribute(LOGIN_INFO);
		if(sessionObj != null) {
			return JSON.parseObject(sessionObj.toString(), Gerent.class);
		}
		return null;
	}
	
	public static void setMobileLoginInfo(HttpServletRequest request, Object content) {
		request.getSession().setAttribute(MOBILE_LOGIN_INFO, JSON.toJSONString(content));
	}
	
	public static User getMobileLoginBean(HttpServletRequest request) {
		Object sessionObj = request.getSession().getAttribute(LOGIN_INFO);
		if(sessionObj != null) {
			return JSON.parseObject(sessionObj.toString(), User.class);
		}
		return null;
	}

	public static void setLogout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("JSESSIONID", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		request.getSession().removeAttribute(LOGIN_INFO);
	}
	
	
}
