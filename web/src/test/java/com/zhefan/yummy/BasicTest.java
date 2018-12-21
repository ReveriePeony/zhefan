package com.zhefan.yummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicTest {

	private Long startTime;

	@Before
	public void start() {
		startTime = System.currentTimeMillis();
		System.err
				.println("=======================================  单元测试Start =======================================");
	}

	@After
	public void end() {
		System.err.println("执行结束 " + (System.currentTimeMillis() - startTime) + "  毫秒");
		System.err.println("=======================================  单元测试End =======================================");
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private JavaMailSender mailSender;

	@Test
	public void testUpload() throws Exception {
//		String url = "http://localhost:8111/file/uploadimg";
//		String saveurl = "http://localhost:8111/file/saveimg";
//		String delurl = "http://localhost:8111/file/delimg";
//		String filePath = "D:\\c3fdfc039245d688acf9cde6a9c27d1ed31b24fc.jpg";
//
//		FileSystemResource resource = new FileSystemResource(new File(filePath));
//		LinkedMultiValueMap<Object, Object> param = new LinkedMultiValueMap<>();
//		param.add("file", resource);
//		param.add("id", 1000);
//		param.add("startFilePath", "upload/1000/temp/1543393154985.jpg");
//		param.add("endFilePath", "upload/1000/1543393154985.jpg");
//
//		JSONObject string = restTemplate.postForObject(delurl, param, JSONObject.class);
//		System.out.println(string);
		
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("reverienight@foxmail.com");
        message.setTo("759730492@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容2222222222222222222222222");

        mailSender.send(message);
	}

}
