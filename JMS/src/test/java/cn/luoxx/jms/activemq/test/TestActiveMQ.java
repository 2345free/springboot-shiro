package cn.luoxx.jms.activemq.test;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.luoxx.jms.activemq.Comsumer;
import cn.luoxx.jms.activemq.Producter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestActiveMQ {

	@Autowired
	private Producter producter;

	@Autowired
	private Comsumer comsumer;

	@Resource
	private JmsTemplate jmsTemplate;

	@Test
	public void testJmsTemplateSend() {

		this.jmsTemplate.send("jmsTemplateMQ", new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("hello activeMQ form JmsTemplate!");
			}
		});
	}

	@Test
	public void testJmsTemplateRecive() {
		this.jmsTemplate.receive("jmsTemplateMQ");
	}

	@Test
	public void testProducter() {
		this.producter.init();
		while (true) {
			try {
				TestActiveMQ.this.producter.sendMessage("testMQ");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Test
	public void testComsumer() {
		this.comsumer.init();
		try {
			TestActiveMQ.this.comsumer.getMessage("testMQ");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
