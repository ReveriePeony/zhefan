package com.zhefan.yummy.wx;

import java.text.SimpleDateFormat;
import java.util.Random;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * 微信统一下单参数
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年3月14日
 *
 */
@Api("微信统一下单参数")
@Data
public class UnifiedPayment {

	/**
	 * (*)公众账号ID 微信支付分配的公众账号ID（企业号corpid即为此appId）
	 */
	private String appid;
	
	/**
	 * (*)商户号 微信支付分配的商户号
	 */
	private String mch_id;
	
	/**
	 * 设备号 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
	 */
	private String device_info = "WEB";
	
	/**
	 * 随机字符串 随机字符串，长度要求在32位以内。
	 */
	private String nonce_str;
	
	/**
	 * (*)签名 通过签名算法计算得出的签名值，详见签名生成算法 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=4_3
	 */
	private String sign;

	/**
	 * 签名类型 签名类型，默认为MD5，支持HMAC-SHA256和MD5。
	 */
	private String sign_type = "MD5";
	
	/**
	 * (*)商品描述  String(128) 商品简单描述，该字段请按照规范传递，具体请见参数规定
	 */
	private String body;
	
	/**
	 * 商品详情	detail	否	String(6000)	 	商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”
	 */
	private String detail;
	
	/**
	 * 附加数据	attach	否	String(127)	深圳分店	附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
	 */
	private String attach;
	
	/**
	 * (*)商户订单号	out_trade_no	是	String(32)	20150806125346	
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|* 且在同一个商户号下唯一。详见商户订单号
	 */
	private String out_trade_no = getOutTradeNo();
	
	/**
	 * 标价币种	fee_type	否	String(16)	CNY	符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
	 */
	private String fee_type;
	
	/**
	 * (*)标价金额	total_fee	是	Int	88	订单总金额，单位为分，详见支付金额
	 */
	private String total_fee;
	
	/**
	 * (*)终端IP	spbill_create_ip	是	String(64)	123.12.12.123	支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
	 */
	private String spbill_create_ip;
	
	/**
	 * 交易起始时间	time_start	否	String(14)	20091225091010	
	 * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 */
	private String time_start;
	
	/**
	 * 交易结束时间	time_expire	否	String(14)	20091227091010	
	 * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
	 * 订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，
	 * 所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
	 * 建议：最短失效时间间隔大于1分钟
	 */
	private String time_expire;
	
	/**
	 * 订单优惠标记	goods_tag	否	String(32)	WXG	订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
	 */
	private String goods_tag;
	
	/**
	 * (*)通知地址	notify_url	是	String(256)	
	 * http://www.weixin.qq.com/wxpay/pay.php	异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
	 */
	private String notify_url;
	
	/**
	 * (*)交易类型	trade_type	是	String(16)	JSAPI 
	 * JSAPI -JSAPI支付 
	 * NATIVE -Native支付 
	 * APP -APP支付 
	 * 说明详见参数规定
	 */
	private String trade_type = "JSAPI";
	
	/**
	 * 商品ID	product_id	否	String(32)	12235413214070356458058	
	 * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
	 */
	private String product_id;
	
	/**
	 * 指定支付方式	limit_pay	否	String(32)	no_credit	上传此参数no_credit--可限制用户不能使用信用卡支付
	 */
	private String limit_pay;
	
	/**
	 * 用户标识	openid	否	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	trade_type=JSAPI时（即JSAPI支付），此参数必传，
	 * 此参数为微信用户在商户对应appid下的唯一标识。
	 * openid如何获取，可参考【获取openid】。
	 * 企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，
	 * 再调用【企业号userid转openid接口】进行转换
	 */
	private String openid;
	
	/**
	 * 电子发票入口开放标识	receipt	否	String(8)	Y	
	 * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
	 */
	private String receipt;
	
	/**
	 * 场景信息	scene_info	否	String(256)	
	 * {"store_info" : {
	 * "id": "SZTX001",
	 * "name": "腾大餐厅",
	 * "area_code": "440305",
	 * "address": "科技园中一路腾讯大厦" }} 
	 * 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，
	 * 商户也可以按需求自己上报相关信息。该字段为JSON对象数据，
	 * 对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，
	 * 字段详细说明请点击行前的+展开
	 */
	private String scene_info;

	private String getOutTradeNo() {
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis());
		StringBuilder no = new StringBuilder("YMM");
		Random random = new Random();
		no.append(time);
		int i = 0;
		while(i < 15) {
			no.append(random.nextInt(10));
			i++;
		}
		return no.toString();
	}
	
}
