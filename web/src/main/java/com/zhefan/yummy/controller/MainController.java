package com.zhefan.yummy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年10月31日
 *
 *
 */
@Api(tags = "公共")
@Controller
public class MainController implements ErrorController {

	private static final String PATH = "/error";

	@Autowired
	private ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {
		return PATH;
	}
//	
//	@GetMapping("/login")
//	public ModelAndView login(HttpServletRequest request) {
//		ModelAndView view = new ModelAndView();
//		view.setViewName("/login.html");
//		return view;
//	}

	@ApiOperation(hidden = true, value = "")
	@RequestMapping(PATH)
	@ResponseBody
	public JSONObject doHandleError(HttpServletRequest request) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> errorAttributesData = errorAttributes.getErrorAttributes(requestAttributes, true);
		Integer status = (Integer) errorAttributesData.get("status");
		String path = (String) errorAttributesData.get("path");
		String messageFound = (String) errorAttributesData.get("message");

		JSONObject reData = new JSONObject();
		reData.put("status", status);
		reData.put("path", path);
		reData.put("message", messageFound);
		return reData;
	}
}
