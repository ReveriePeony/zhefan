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
 * 商家(后台)账号 - 微信信息
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Data
@Accessors(chain = true)
@TableName("t_weixin_business")
public class WeixinBusiness extends Model<WeixinBusiness> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家(后台)账号ID
     */
	private Integer gerentId;
    /**
     * 微信appid
     */
	private String appId;
    /**
     * 微信密钥
     */
	private String appSecret;
    /**
     * 创建时间
     */
	private String creationTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
