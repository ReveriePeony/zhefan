package com.zhefan.yummy.exception;

import com.zhefan.yummy.enums.ResponseEnums;

public class BusinessException extends BaseException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7213501605261147050L;

	public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(code, message);
    }

    public BusinessException(ResponseEnums responseEnums) {
        super(responseEnums);
    }
}
