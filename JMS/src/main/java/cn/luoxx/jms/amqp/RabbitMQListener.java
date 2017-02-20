package cn.luoxx.jms.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

	@RabbitListener(queues = "rabbitMQ")
	public void processMessage(String content) {
		System.out.println("rabbitMQ收到消息:" + content);
	}

}
