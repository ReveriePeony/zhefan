package com.zhefan.yummy.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Api("用户")
@Data
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
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
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;
	/**
	 * 创建时间
	 */
	private String creationTime;
	/**
	 * 状态 0-注销 1-正常
	 */
	@ApiModelProperty("状态 0-注销 1-正常")
	private Integer status = 1;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public void copyUserInfo(JSONObject userInfo) {
		this.name = userInfo.getString("nickName");
		this.avatar = userInfo.getString("avatarUrl");
		this.creationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
		this.openid = userInfo.getString("openId");
		this.unionid = userInfo.getString("unionId");
		this.nickname = userInfo.getString("nickName");
		this.sex = userInfo.getInteger("gender");
		this.province = userInfo.getString("province");
		this.city = userInfo.getString("city");
		this.country = userInfo.getString("country");
		this.headimgurl = userInfo.getString("avatarUrl");
	}
	
}
