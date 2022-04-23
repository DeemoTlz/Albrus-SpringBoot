package com.deemo.mq;

import com.deemo.mq.boot.JmsBootProduce;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = Boot03MqApplication.class)
@WebAppConfiguration
class Boot03MqApplicationTests {

	@Resource
	private JmsBootProduce jmsBootProduce;


	@Test
	void contextLoads() {

	}

	@Test
	public void testSend() {
		jmsBootProduce.produce();
	}

}
