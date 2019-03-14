package com.zhefan.yummy.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhefan.yummy.entity.User;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.BaseException;
import com.zhefan.yummy.util.SessionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 移动端 拦截器
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年3月13日
 *
 */
@Slf4j
@Component
public class MobileAuthInterceptor extends HandlerInterceptorAdapter {

	private List<String> patterns;

	{
		patterns = new ArrayList<>();
		patterns.add(".*mobile/\\d+/home.*");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug(request.getLocalAddr() + " " + request.getRequestURL());
		User user = SessionUtil.getMobileLoginBean(request);
		if (user == null) {
			throw new BaseException(ResponseEnums.WX_NOT_LOGIN, "/mobile/wx/h5auth");
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
