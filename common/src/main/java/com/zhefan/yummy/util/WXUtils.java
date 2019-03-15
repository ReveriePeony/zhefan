package com.zhefan.yummy.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.enums.ResponseEnums;
import com.zhefan.yummy.exception.ResponseEntityException;
import com.zhefan.yummy.wx.UnifiedPayment;

import lombok.extern.slf4j.Slf4j;

/**
 * 微信工具
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年1月31日
 *
 */
@Slf4j
@Component
public class WXUtils {

	private static final String LANG = "zh_CN";

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${wx.appid}")
	private String appid;
	
	@Value("${wx.appsecrret}")
	private String appsecrret;
	
	@Value("${wx.signkey}")
	private String signkey;
	
	/**
	 * 
	 * @return 小程序 access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
		expires_in	access_token接口调用凭证超时时间，单位（秒）
		refresh_token	用户刷新access_token
		openid	用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
		scope	用户授权的作用域，使用逗号（,）分隔
	 */
	public JSONObject getMiniAccessToken(String appid, String appsecrret) {
		String url = String.format(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", 
				appid, appsecrret);
		ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class, new Object() {});
		if(responseEntity.getStatusCodeValue() != 200) {
			throw new ResponseEntityException(ResponseEnums.WX_REQUET_ERROR);
		}
		return responseEntity.getBody();
	}
	
	/**
	 * 小程序接口
	 * @param appid
	 * @param appsecrret
	 * @param code
	 * @return openid	string	用户唯一标识
				session_key	string	会话密钥
				unionid	string	用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
				errcode	number	错误码
				errmsg	string	错误信息
	 */
	public JSONObject getMiniLoginInfo(String appid, String appsecrret, String code) {
		String url = String.format(
				"https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", 
				appid, appsecrret, code);
		ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class, new Object() {});
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		return responseEntity.getBody();
	}
	
	/**
	 * 小程序 公众号 获取用户基本信息(UnionID机制)
	 * @return {
		    "subscribe": 1, 
		    "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
		    "nickname": "Band", 
		    "sex": 1, 
		    "language": "zh_CN", 
		    "city": "广州", 
		    "province": "广东", 
		    "country": "中国", 
		    "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
		    "subscribe_time": 1382694957,
		    "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
		    "remark": "",
		    "groupid": 0,
		    "tagid_list":[128,2],
		    "subscribe_scene": "ADD_SCENE_QR_CODE",
		    "qr_scene": 98765,
		    "qr_scene_str": ""
		}
		{"errcode":40013,"errmsg":"invalid appid"}
	 */
	public JSONObject getMiniUserInfo(String openid, String appid, String appsecrret) {
		String url = String.format(
				"https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN", 
				getMiniAccessToken(appid, appsecrret).getString("access_token"), openid);
		ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class, new Object() {});
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		return responseEntity.getBody();
	}
	
	/**
	 * 小程序 解密encryptedData
	 * @param encryptedData
	 * @param sessionkey
	 * @param iv
	 * @return {
		  "openId": "OPENID",
		  "nickName": "NICKNAME",
		  "gender": GENDER,
		  "city": "CITY",
		  "province": "PROVINCE",
		  "country": "COUNTRY",
		  "avatarUrl": "AVATARURL",
		  "unionId": "UNIONID",
		  "watermark": {
		    "appid": "APPID",
		    "timestamp": TIMESTAMP
		  }
		}
	 */
	public JSONObject getDecryptUserInfo(String encryptedData, String sessionkey, String iv) {
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(sessionkey);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);
		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result);
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (NoSuchPaddingException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (InvalidParameterSpecException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (BadPaddingException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (InvalidKeyException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		} catch (NoSuchProviderException e) {
			log.error(e.getMessage(), e.getCause());
			ResponseDTO.error(e.getMessage());
		}
		return null;

	}

	/**
	 * 获取H5微信授权URL
	 * @param reUrl 回调路径
	 * @param state 保留数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getOauth2Url(String reUrl, String state) throws UnsupportedEncodingException {
		
		StringBuilder oauthUrl = new StringBuilder("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		oauthUrl.append(appid);
		oauthUrl.append("&redirect_uri=");
		oauthUrl.append(URLEncoder.encode(reUrl, "UTF-8"));
		oauthUrl.append("&response_type=code&scope=snsapi_userinfo&state=");
		oauthUrl.append(state);
		oauthUrl.append("#wechat_redirect");
		return oauthUrl.toString();
	}
	
	/**
	 * 通过code换取网页授权access_token
	 * @param code
	 * @return
	 */
	public JSONObject getH5AccessToken(String code) {
		String url = String.format(
				"https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", 
				appid, appsecrret, code);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, new Object() {});
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		return JSONObject.parseObject(responseEntity.getBody());
	}
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public JSONObject getH5UserInfo(String accessToken, String openid, String lang) throws UnsupportedEncodingException {
		String url = String.format(
				"https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s", 
				accessToken, openid, lang == null ? LANG : lang);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, new Object() {});
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		return JSONObject.parseObject(new String(responseEntity.getBody().getBytes("ISO-8859-1"), "UTF-8"));
	}
	
	/**
	 * 统一下单接口
	 * @return 预支付交易会话标识	prepay_id	是	String(64)	wx201410272009395522657a690389285100	
		  微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
	 * @throws Exception 
	 */
	public JSONObject UnifiedOrderAPI(UnifiedPayment unifiedPayment) throws Exception {
		String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		unifiedPayment.setNonce_str(getRandomStr());
		JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(unifiedPayment));
		unifiedPayment.setSign(getPaySign(json));
		String param = XmlBuilder.object2XmlStr(unifiedPayment);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, param);
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		Object result = XmlBuilder.xmlStr2Object(JSONObject.class, responseEntity.getBody());
		return JSONObject.parseObject(result.toString());
	}
	
	/**
	 * 查询订单
	 * @return 交易状态	trade_state	是	String(32)	SUCCESS	
	 		SUCCESS—支付成功
	 		REFUND—转入退款
	 		NOTPAY—未支付
	 		CLOSED—已关闭
	 		REVOKED—已撤销（付款码支付）
	 		USERPAYING--用户支付中（付款码支付）
	 		PAYERROR--支付失败(其他原因，如银行返回失败)
	 		支付状态机请见下单API页面
	 * @throws Exception 
	 */
	public JSONObject QueryOrderAPI(UnifiedPayment unifiedPayment) throws Exception {
		String url = "https://api.mch.weixin.qq.com/pay/orderquery";
		unifiedPayment.setNonce_str(getRandomStr());
		JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(unifiedPayment));
		unifiedPayment.setSign(getPaySign(json));
		String param = XmlBuilder.object2XmlStr(unifiedPayment);
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, param);
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		Object result = XmlBuilder.xmlStr2Object(JSONObject.class, responseEntity.getBody());
		return JSONObject.parseObject(result.toString());
	}
	
	/**
	 * 退款
	 * @return 
	 * @throws Exception 
	 */
	public JSONObject RefundAPI(UnifiedPayment unifiedPayment) throws Exception {
		
		return null;
	}
	
	private String getPaySign(JSONObject json) {
		StringBuilder param = new StringBuilder();
		List<String> keys = new ArrayList<String>();
		json.keySet().forEach(s -> {
			if(StringUtils.isNotBlank(json.getString(s))) {
				keys.add(s);
			}
		});
		Collections.sort(keys);
		keys.forEach(key -> {
			param.append(key).append("=").append(json.getString(key)).append("&");
		});
		param.append("key=").append(signkey);
		String md5Hex = DigestUtils.md5Hex(param.toString());
		return md5Hex.toUpperCase();
	}
	
	private String getRandomStr() {
		StringBuilder r = new StringBuilder();
		r.append(Math.random() * 100000);
		r.append(Math.random() * 100000);
		r.append(Math.random() * 100000);
		return r.toString();
	}
	
	public static void main(String[] args) {
//		UnifiedPayment up = new UnifiedPayment();
//		up.setAppid("wxd930ea5d5a258f4f");
//		up.setMch_id("10000100");
//		up.setDevice_info("1000");
//		up.setBody("test");
//		up.setNonce_str("ibuaiVcKdpRxkhJA");
		JSONObject json = JSONObject.parseObject("{\"nonce_str\":\"ibuaiVcKdpRxkhJA\",\"device_info\":\"1000\",\"appid\":\"wxd930ea5d5a258f4f\",\"body\":\"test\",\"mch_id\":\"10000100\"}");
		StringBuilder param = new StringBuilder();
		List<String> keys = new ArrayList<String>();
		json.keySet().forEach(s -> {
			if(StringUtils.isNotBlank(json.getString(s))) {
				keys.add(s);
			}
		});
		Collections.sort(keys);
		keys.forEach(key -> {
			param.append(key).append("=").append(json.getString(key)).append("&");
		});
		param.append("key=").append("192006250b4c09247ec02edce69f6a2d");
		String md5Hex = DigestUtils.md5Hex(param.toString());
		System.err.println(md5Hex.toUpperCase());
	}
	
}
