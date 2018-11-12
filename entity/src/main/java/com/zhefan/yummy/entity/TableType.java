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
 * 餐桌类型
 * </p>
 *
 * @author ReverirNight@Foxmail.com
 * @since 2018-11-12
 */
@Data
@Accessors(chain = true)
@TableName("t_table_type")
public class TableType extends Model<TableType> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 商家(后台)店铺ID
     */
	private Integer shopId;
    /**
     * 餐桌类型名称
     */
	private String tableTypeName;
    /**
     * 创建者ID
     */
	private Integer creatorId;
    /**
     * 创建时间
     */
	private String creationTime;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
