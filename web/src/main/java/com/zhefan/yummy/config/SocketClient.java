package com.zhefan.yummy.config;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zhefan.yummy.vo.SocketMessage;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * 
 * @author ReverirNight@Foxmail.com
 * @datetime 2018年11月16日
 *
 */
@Component
public class SocketClient {

	private static final String CLIENT_ID = "javaClient";

	@Autowired
	private Socket socket;
	
	@Value("${socket.server.host}")
	private String host;
	
	@Value("${socket.server.port}")
	private String port;

	public void sendMessage(String targetClientId, String content) {
		socket.emit("clientEvent", JSON.toJSONString(new SocketMessage(CLIENT_ID, targetClientId, content)));
	}
	

	@Bean()
	private Socket initSocket() throws URISyntaxException {
		socket = IO.socket(host + ":" + port + "?clientid=" + CLIENT_ID + "&token="
				+ DigestUtils.md5Hex("yummy" + new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())));
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				for (Object a : args) {
					System.err.println("EVENT_CONNECT " + a);
				}
			}

		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				for (Object a : args) {
					System.err.println("EVENT_CONNECT " + a);
				}
			}
		}).on("clientEvent", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				for (Object a : args) {
					System.err.println("EVENT_CONNECT " + a);
				}
			}
		});
		socket.connect();
		return socket;
	}
}