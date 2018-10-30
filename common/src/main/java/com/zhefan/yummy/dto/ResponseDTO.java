package com.zhefan.yummy.dto;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing.DEFAULT_TYPING;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhefan.yummy.enums.ResponseEnums;

@SuppressWarnings("serial")
@JsonSerialize(typing = DEFAULT_TYPING)
public class ResponseDTO<T> implements Serializable {

	/* 状态码 */
	private int code;

	/* 返回消息 */
	private String msg;

	/* 泛型数据 */
	private T data;

	protected ResponseDTO(int code) {
		this.code = code;
	}

	protected ResponseDTO(int code, T data) {
		this.code = code;
		this.data = data;
	}

	protected ResponseDTO(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	protected ResponseDTO(int code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static <T> ResponseDTO<T> createSuccess() {
		return createSuccess(ResponseEnums.SUCCESS.getMsg());
	}

	public static <T> ResponseDTO<T> createSuccess(T data) {
		return createSuccess(ResponseEnums.SUCCESS.getMsg(), data);
	}

	public static <T> ResponseDTO<T> createSuccess(String msg) {
		return createSuccess(msg, null);
	}

	public static <T> ResponseDTO<T> createSuccess(String msg, T data) {
		return createSuccess(ResponseEnums.SUCCESS.getCode(), msg, data);
	}

	public static <T> ResponseDTO<T> createSuccess(int code, String msg, T data) {
		return new ResponseDTO<>(code, msg, data);
	}

	public static <T> ResponseDTO<T> createError() {
		return createError(ResponseEnums.SYSTEM_ERROR.getCode(), ResponseEnums.SYSTEM_ERROR.getMsg());
	}

	public static <T> ResponseDTO<T> createError(String errorMessage) {
		return createError(ResponseEnums.SYSTEM_ERROR.getCode(), errorMessage);
	}

	public static <T> ResponseDTO<T> createError(int errorCode, String errorMessage) {
		return new ResponseDTO<>(errorCode, errorMessage);
	}
	
	public static <T> ResponseDTO<T> createError(ResponseEnums responseEnums) {
		return new ResponseDTO<>(responseEnums.getCode(), responseEnums.getMsg());
	}

	@JsonIgnore
	public boolean isSuccess() {
		return this.code == ResponseEnums.SUCCESS.getCode();
	}

	public int getCode() {
		return code;
	}

	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}

}
