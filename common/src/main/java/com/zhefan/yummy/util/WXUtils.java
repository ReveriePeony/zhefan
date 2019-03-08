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
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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
		ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class, new Object() {});
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		return responseEntity.getBody();
	}
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * @return
	 */
	public JSONObject getH5UserInfo(String accessToken, String openid, String lang) {
		String url = String.format(
				"https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=%s", 
				accessToken, openid, lang == null ? LANG : lang);
		ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(url, JSONObject.class, new Object() {});
		if(responseEntity.getStatusCodeValue() != 200) {
			ResponseDTO.error(ResponseEnums.WX_REQUET_ERROR);
		}
		return responseEntity.getBody();
	}
	
}
