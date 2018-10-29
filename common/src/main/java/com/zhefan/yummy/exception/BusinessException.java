package com.zhefan.yummy.exception;

import com.zhefan.yummy.enums.ResponseEnums;

public class BusinessException extends BaseException {

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
