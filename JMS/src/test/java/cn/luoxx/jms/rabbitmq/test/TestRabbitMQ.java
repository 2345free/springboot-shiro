package cn.luoxx.jms.rabbitmq.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRabbitMQ {

	// private final AmqpAdmin amqpAdmin;
	// private final AmqpTemplate amqpTemplate;
	//
	// @Autowired
	// public TestRabbitMQ(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
	// this.amqpAdmin = amqpAdmin;
	// this.amqpTemplate = amqpTemplate;
	// }

	@Resource
	private RabbitMessagingTemplate msgTemplate;

	@Test
	@Scheduled(fixedDelay = 3000)
	public void testSendMsg() {
		// "from RabbitMQ's message!"
		this.msgTemplate.convertAndSend("rabbitMQ", "from RabbitMQ's message!");
	}

}
