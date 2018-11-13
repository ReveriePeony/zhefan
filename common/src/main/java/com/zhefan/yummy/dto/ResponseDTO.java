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

	public static <T> ResponseDTO<T> success() {
		return success(ResponseEnums.SUCCESS.getMsg());
	}

	public static <T> ResponseDTO<T> success(T data) {
		return success(ResponseEnums.SUCCESS.getMsg(), data);
	}

	public static <T> ResponseDTO<T> success(String msg) {
		return success(msg, null);
	}

	public static <T> ResponseDTO<T> success(String msg, T data) {
		return success(ResponseEnums.SUCCESS.getCode(), msg, data);
	}

	public static <T> ResponseDTO<T> success(int code, String msg, T data) {
		return new ResponseDTO<>(code, msg, data);
	}

	public static <T> ResponseDTO<T> error() {
		return error(ResponseEnums.SYSTEM_ERROR.getCode(), ResponseEnums.SYSTEM_ERROR.getMsg());
	}

	public static <T> ResponseDTO<T> error(String errorMessage) {
		return error(ResponseEnums.SYSTEM_ERROR.getCode(), errorMessage);
	}

	public static <T> ResponseDTO<T> error(int errorCode, String errorMessage) {
		return new ResponseDTO<>(errorCode, errorMessage);
	}
	
	public static <T> ResponseDTO<T> error(ResponseEnums responseEnums) {
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
