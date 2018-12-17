package com.zhefan.yummy.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.BaseException;
import com.zhefan.yummy.util.RedisCacheUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 拦截器
 * 
 * @author Reverien9@gmail.com 2017年11月8日 下午2:54:16
 */
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	RedisCacheUtil redisCacheUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getParameter("token");
		log.debug(request.getLocalAddr() + " " + request.getRequestURL() + " " + token);
		String md5Hex = DigestUtils
				.md5Hex("fi" + new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()) + "le");
		if (!md5Hex.equals(token)) {
			throw new BaseException(ResponseEnums.JWT_TOKEN_EXPIRED);
		}
		return true;

	}

}
