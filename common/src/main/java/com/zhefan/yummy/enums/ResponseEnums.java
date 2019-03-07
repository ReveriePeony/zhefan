package com.zhefan.yummy.enums;

public enum ResponseEnums {
	
    SUCCESS(0, "成功"),
    
    AUTHENTICATION(10, "非法认证"),
    JWT_TOKEN_EXPIRED(11, "TOKEN失效"),
    DATA_DOES_NOT_EXIST(12, "数据不存在"),
    BAD_REQUEST(13, "非法请求"),

    LOGIN_ERROR(1000, "登录失败"),
    NEED_LOGIN(1001, "需要登录"),
    SAVE_ERROR(1002, "保存失败"),
    DELETE_ERROR(1003, "删除失败"),
    OPERATING_ERROR(1004, "操作失败"),
    ORDER_STATUS_ERROR(1005, "订单状态错误"),
    PAY_STATUS_ERROR(1006, "支付状态错误"),
    REFUNDS_ERROR(1008, "退款失败"),
    FAILED_TO_OBTAIN_MEMBER_INFORMATION(1007, "获取会员信息失败"),
    BOOK_FAILED(1008, "下单失败"),
    SHOP_ID_NULL(1009, "shopId不能为空"),
    ORDER_ID_NULL(1010, "orderId不能为空"),
    NAME_ID_NULL(1011, "名称(账号)不能为空"),
    PWD_ID_NULL(1012, "密码不能为空"),
    ID_NULL(1013, "ID不能为空"),
    FILE_SAVE_ERROR(1014, "文件保存失败"),
    CANCEL_ERROR(1015, "取消失败"),
    UPDATE_ERROR(1016, "更新失败"),
    TABLENAME_NULL(1017, "桌号不能为空"),
    USER_ID_IS_NULL(1017, "用户ID不能为空"),
    WX_REQUET_ERROR(1018, "访问微信服务器失败"),
    ACCOUNT_PWD_ERROR(1019, "账号或密码错误"),

    IMAGE_ERROR(1999, "图片保存失败"),

    SIGNATURE_ERROR(3001, "签名错误"),
    UNKNOWN_ERROR(9998, "未知错误"),
    SYSTEM_ERROR(9999, "系统错误");


	private final int code;
    private final String msg;

    ResponseEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
