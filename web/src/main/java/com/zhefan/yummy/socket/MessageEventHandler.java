package com.zhefan.yummy.socket;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.zhefan.yummy.dto.ResponseDTO;
import com.zhefan.yummy.util.RedisCacheUtil;

@Component
public class MessageEventHandler {

	@Autowired
	private RedisCacheUtil redisCacheUtil;

	private final SocketIOServer server;

	@Autowired
	public MessageEventHandler(SocketIOServer server) {
		this.server = server;
	}

	// 添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
	// 方便后面发送消息时查找到对应的目标client,
	@OnConnect
	public void onConnect(SocketIOClient client) {
		String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
		Long mostSignificantBits = client.getSessionId().getMostSignificantBits();
		Long leastSignificantBits = client.getSessionId().getLeastSignificantBits();
		redisCacheUtil.set(clientId, mostSignificantBits + "SSK" + leastSignificantBits, 24 * 60 * 60L);
	}

	// 添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
	@OnDisconnect
	public void onDisconnect(SocketIOClient client) {
		String clientId = client.getHandshakeData().getSingleUrlParam("clientid");
		redisCacheUtil.remove(clientId);
	}

	// 消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
	@OnEvent(value = "event")
	public void onEvent(SocketIOClient client, AckRequest request, SocketMessage data) {
		String targetClientId = data.getTargetClientId();
		Object target = redisCacheUtil.get(targetClientId);
		if (target != null) {
			String[] targetSession = target.toString().split("SSK");
			client.sendEvent("event", ResponseDTO.success("success", data.getMsgContent()));
			server.getClient(new UUID(Long.valueOf(targetSession[0]), Long.valueOf(targetSession[1])))
					.sendEvent("event", ResponseDTO.success("success", data.getMsgContent()));
		} else {
			client.sendEvent("event", ResponseDTO.error("发送目标不存在"));
		}
	}
}