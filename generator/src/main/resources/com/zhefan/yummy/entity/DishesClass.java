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
 * 菜品类型
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-10-30
 */
@Data
@Accessors(chain = true)
@TableName("t_dishes_class")
public class DishesClass extends Model<DishesClass> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家(后台)账号ID
     */
	private Integer gerentId;
    /**
     * 商家(后台)店铺ID
     */
	private Integer shopId;
    /**
     * 菜品类型名称
     */
	private String dishesClassName;
    /**
     * 创建时间
     */
	private String creationTime;
    /**
     * 创建者名称
     */
	private String creator;
    /**
     * 创建者ID
     */
	private Integer creatorId;
    /**
     * 使用状态 0-注销 1-正常
     */
	private Integer status;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
