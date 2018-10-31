package com.zhefan.yummy.util;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zhefan.yummy.entity.Gerent;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月31日
 *
 */
public class SessionUtil {
	
	private static final String LOGIN_INFO = "login_user"; 
	
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
	
	
}
