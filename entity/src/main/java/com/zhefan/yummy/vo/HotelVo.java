package com.zhefan.yummy.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "新增酒店 - 酒店列表")
public class HotelVo {
    @ApiModelProperty("门店ID")
    private Integer shopId;

    @ApiModelProperty("酒店ID")
    private Integer hotelId;

    @ApiModelProperty("酒店名称")
    private String name;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("图片地址")
    private String logo;

    @ApiModelProperty("经度")
    private Double longitude;

    @ApiModelProperty("纬度")
    private Double latitude;

    @ApiModelProperty("酒店描述")
    private String desc;
}