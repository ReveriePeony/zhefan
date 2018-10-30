package com.zhefan.yummy.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.BaseException;
import com.zhefan.yummy.exception.BusinessException;
import com.zhefan.yummy.exception.ResponseEntityException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	/**
	 * 默认异常处理
	 *
	 * @param request 请求信息
	 * @param e       Exception
	 * @return ResponseErrorDTO
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseDTO defaultErrorHandler(HttpServletRequest request, Exception e) {
		log.error("请求地址：{} , 系统异常详细：", request.getRequestURL(), e);
		return ResponseDTO.createError(ResponseEnums.SYSTEM_ERROR.getCode(),
				ResponseEnums.SYSTEM_ERROR.getMsg());
	}

	/**
	 * 统一自定义异常处理
	 *
	 * @param request 请求信息
	 * @param e       BaseException
	 * @return ResponseErrorDTO
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@ExceptionHandler(value = BaseException.class)
	public ResponseDTO defaultCustomErrorHandler(HttpServletRequest request, BaseException e) {
		log.error("异常原因：{} , 异常信息：{} , 请求地址：{}", e.getCause(), e.getMessage(), request.getRequestURL(), e);
		if (e instanceof ResponseEntityException || e instanceof BusinessException) {
			return ResponseDTO.createSuccess(e.getCode(), e.getMessage(), e.getRedirectUrl());
		} else {
			return ResponseDTO.createSuccess(e.getCode(), e.getMessage(), e.getRedirectUrl());
		}
	}
}
