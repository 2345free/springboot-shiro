package cn.luoxx.jms.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQListener {

	@JmsListener(destination = "jmsTemplateMQ", containerFactory = "myFactory")
	public void processMessage(String content) {
		System.out.println("收到消息:" + content);
	}

}
