package com.zhefan.yummy.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zhefan.yummy.service.THotelService;
import com.zhefan.yummy.util.RedisCacheUtil;

/**
 * 拦截器
 * @author Reverien9@gmail.com
 * 2017年11月8日 下午2:54:16
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
    RedisCacheUtil redisCacheUtil;
    
    @Autowired
	THotelService tHotelService;
	
	private List<String> patterns;
	
	{
		patterns = new ArrayList<>();
		patterns.add(".*78CDF1/\\d+/home.*");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.err.println(request.getLocalAddr() + " " + request.getRequestURL());
		System.err.println(regex(request.getRequestURL().toString()));
//		if (SessionUtils.getLoginUser(request) != null)
//		if(request.getRequestURL().indexOf("78CDF1") != -1) {
//			if(regex(request.getRequestURL().toString())) {
//				//
//				THotel hotel = tHotelService.selectById(hotelId);
//		    	Member member = SessionUtils.getLoginMember(request, hotel.getBusId());
//		    	if(StringUtils.isEmpty(member) || StringUtils.isEmpty(member.getId())) {
//		    		Map<String, Object> param = new HashMap<>();
//		    		param.put("busId", hotel.getBusId());
//		    		param.put("requestUrl", request.getRequestURL().toString());
//		    		String url = null;
//					try {
//						url = authorizeMember(request, param);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//		    		if(!StringUtils.isEmpty(url)) {
//		    			response.sendRedirect(url);
//		    			return false;
//		    		}
//		    	}
//		    	//
//			}else {
//				return true;
//			}
//		}else {
//			response.sendRedirect("/");
//			return false;
			return true;            	
//		}
	}
	
	private boolean regex(String url) {
		for(String pattern : patterns) {
			if(Pattern.matches(pattern, url)) {
				return true;
			}
		}
		return false;
	}
	
}
