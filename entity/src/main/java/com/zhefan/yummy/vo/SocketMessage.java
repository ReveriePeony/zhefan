package com.zhefan.yummy.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SocketMessage implements Serializable {

	private static final long serialVersionUID = 8084783288500935480L;

	@ApiModelProperty("发送者id")
	private String sourceClientId;

	@ApiModelProperty("接受者id")
	private String targetClientId;

//	@ApiModelProperty("消息类型")
//	private String msgType;

	@ApiModelProperty("消息内容")
	private String msgContent;

	public SocketMessage() {
	}

	public SocketMessage(String sourceClientId, String targetClientId, String msgContent) {
		super();
		this.sourceClientId = sourceClientId;
		this.targetClientId = targetClientId;
		this.msgContent = msgContent;
	}

}
