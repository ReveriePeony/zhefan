package com.zhefan.yummy.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhefan.yummy.entity.Gerent;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.BaseException;
import com.zhefan.yummy.util.SessionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 拦截器
 * 
 * @author Reverien9@gmail.com 2017年11月8日 下午2:54:16
 */
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	private List<String> patterns;

	{
		patterns = new ArrayList<>();
		patterns.add(".*mobile/\\d+/home.*");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug(request.getLocalAddr() + " " + request.getRequestURL());
		Gerent gerent = SessionUtil.getLoginBean(request);
		if (gerent == null) {
//			response.sendRedirect("/login");
			throw new BaseException(ResponseEnums.NEED_LOGIN, "/login");
//			return false;
		}
		return true;

	}

	@SuppressWarnings("unused")
	private boolean regex(String url) {
		for (String pattern : patterns) {
			if (Pattern.matches(pattern, url)) {
				return true;
			}
		}
		return false;
	}

}
