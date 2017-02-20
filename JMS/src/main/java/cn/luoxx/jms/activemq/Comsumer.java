package cn.luoxx.jms.activemq;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class Comsumer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	ConnectionFactory connectionFactory;

	Connection connection;

	Session session;

	ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();
	AtomicInteger count = new AtomicInteger();

	public void init() {
		try {
			this.connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();
			this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void getMessage(String disname) {
		try {
			Queue queue = this.session.createQueue(disname);
			MessageConsumer consumer = null;

			if (this.threadLocal.get() != null) {
				consumer = this.threadLocal.get();
			} else {
				consumer = this.session.createConsumer(queue);
				this.threadLocal.set(consumer);
			}
			while (true) {
				Thread.sleep(1000);
				TextMessage msg = (TextMessage) consumer.receive();
				if (msg != null) {
					msg.acknowledge();
					System.out.println(Thread.currentThread().getName() + ": Consumer:我是消费者，我正在消费Msg" + msg.getText()
					+ "--->" + this.count.getAndIncrement());
				} else {
					break;
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
