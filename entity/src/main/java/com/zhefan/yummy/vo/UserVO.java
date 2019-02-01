package com.zhefan.yummy.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户
 * @author ReverirNight@Foxmail.com
 * @datetime 2019年1月31日
 *
 */
@Api("用户")
@Data
public class UserVO {

	private Integer id;
	/**
	 * 名称
	 */
	@ApiModelProperty("名称")
	private String name;
	/**
	 * 头像
	 */
	@ApiModelProperty("头像")
	private String avatar;
	/**
	 * 创建时间
	 */
	private String creationTime;
	/**
	 * 微信授权信息 用户的唯一标识
	 */
	@ApiModelProperty("微信授权信息 用户的唯一标识")
	private String openid;
	/**
	 * 微信授权信息 用户在开放平台的唯一标识符
	 */
	@ApiModelProperty("微信授权信息 用户在开放平台的唯一标识符")
	private String unionid;
	/**
	 * 微信授权信息 用户昵称
	 */
	@ApiModelProperty("微信授权信息 用户昵称")
	private String nickname;
	/**
	 * 微信授权信息 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	@ApiModelProperty("微信授权信息 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
	private Integer sex;
	/**
	 * 微信授权信息 用户填写的省份
	 */
	@ApiModelProperty("微信授权信息 用户填写的省份")
	private String province;
	/**
	 * 微信授权信息 用户填写的城市
	 */
	@ApiModelProperty("微信授权信息 用户填写的城市")
	private String city;
	/**
	 * 微信授权信息 国家
	 */
	@ApiModelProperty("微信授权信息 国家")
	private String country;
	/**
	 * 微信授权信息 用户头像
	 */
	@ApiModelProperty("微信授权信息 用户头像")
	private String headimgurl;
	/**
	 * 微信授权信息 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
	 */
	@ApiModelProperty("微信授权信息 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）")
	private String privilege;

}
