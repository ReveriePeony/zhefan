package com.zhefan.yummy.socket;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

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

	public static void main(String[] args) throws URISyntaxException {
		Socket socket = IO.socket("http://localhost:8888?clientid=clientTest&token="
				+ DigestUtils.md5Hex("yummy" + new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis())));
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				for(Object a : args) {
					System.err.println("EVENT_CONNECT " + a);
				}
			}

		}).on("event", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				for(Object a : args) {
					System.err.println("EVENT_CONNECT " + a);
				}
			}
		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				for(Object a : args) {
					System.err.println("EVENT_CONNECT " + a);
				}
			}
		});
		socket.connect();
		socket.emit("clientEvent", "testclient1@smsg@socket testsssssssssssssssssssssss");
//		socket.disconnect();
	}
}
