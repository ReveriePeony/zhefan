package com.zhefan.yummy.socket;

import java.text.SimpleDateFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

@Component
public class SocketConfig {
	
	@Value("${wss.server.host}")
	private String host;

	@Value("${wss.server.port}")
	private Integer port;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		config.setHostname(host);
		config.setPort(port);

		// 该处可以用来进行身份验证
		config.setAuthorizationListener(new AuthorizationListener() {
			@Override
			public boolean isAuthorized(HandshakeData data) { // http://localhost:8081?username=test&password=test
				// 例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
//				String username = data.getSingleUrlParam("username");
//				String password = data.getSingleUrlParam("password");///
				String tk = DigestUtils.md5Hex("yummy" + new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()));
				String token = data.getSingleUrlParam("token");
				if (!tk.equals(token))
					return false;
				return true;
			}
		});

		final SocketIOServer server = new SocketIOServer(config);
		return server;
	}

	@Bean
	public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
		return new SpringAnnotationScanner(socketServer);
	}

}