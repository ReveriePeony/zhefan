package com.zhefan.yummy.socket;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SocketMessage {
//
//	@ApiModelProperty("源客户端id")
//	private String sourceClientId;

	@ApiModelProperty("目标客户端id")
	private String targetClientId;

//	@ApiModelProperty("消息类型")
//	private String msgType;

	@ApiModelProperty("消息内容")
	private String msgContent;

}
