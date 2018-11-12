package com.zhefan.yummy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

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
@Data
@Accessors(chain = true)
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名称
     */
	private String name;
    /**
     * 头像
     */
	private String avatar;
    /**
     * 密码
     */
	private String password;
    /**
     * 创建时间
     */
	private String creationTime;
    /**
     * 状态 0-注销 1-正常
     */
	private Integer status;
    /**
     * 微信授权信息 用户的唯一标识
     */
	private String openid;
    /**
     * 微信授权信息 用户昵称
     */
	private String nickname;
    /**
     * 微信授权信息 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
	private Integer sex;
    /**
     * 微信授权信息 用户填写的省份
     */
	private String province;
    /**
     * 微信授权信息 用户填写的城市
     */
	private String city;
    /**
     * 微信授权信息 国家
     */
	private String country;
    /**
     * 微信授权信息 用户头像
     */
	private String headimgurl;
    /**
     * 微信授权信息 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
	private String privilege;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
