package com.zhefan.yummy;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicTest {
	
	private Long startTime;

    @Before
    public void start() {
    	startTime = System.currentTimeMillis();
        System.err.println("=======================================  单元测试Start =======================================");
    }

    @After
    public void end() {
    	System.err.println("执行结束 " + (System.currentTimeMillis() - startTime) + "  毫秒");
    	System.err.println("=======================================  单元测试End =======================================");
    }

}
