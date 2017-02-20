package cn.luoxx.jms.activemq;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class Producter {

	// ActiveMq 的默认用户名
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	// ActiveMq 的默认登录密码
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	// ActiveMQ 的链接地址
	private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
	AtomicInteger count = new AtomicInteger(0);
	// 链接工厂
	ConnectionFactory connectionFactory;
	// 链接对象
	Connection connection;
	// 事务管理
	Session session;
	ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<>();

	public void init() {
		try {
			// 创建一个链接工厂
			this.connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			// 从工厂中创建一个链接
			this.connection = this.connectionFactory.createConnection();
			// 开启链接 connection.start();
			// 创建一个事务（这里通过参数可以设置事务的级别）
			this.session = this.connection.createSession(true, Session.SESSION_TRANSACTED);
			System.out.println("生产者Producter初始化完毕...");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String disname) {
		try {
			// 创建一个消息队列
			Queue queue = this.session.createQueue(disname);
			// 消息生产者
			MessageProducer messageProducer = null;
			if (this.threadLocal.get() != null) {
				messageProducer = this.threadLocal.get();
			} else {
				messageProducer = this.session.createProducer(queue);
				this.threadLocal.set(messageProducer);
			}
			while (true) {
				Thread.sleep(1000);
				int num = this.count.getAndIncrement();
				// 创建一条消息
				TextMessage msg = this.session.createTextMessage(
						Thread.currentThread().getName() + "productor:我是大帅哥，我现在正在生产东西！,count:" + num);
				System.out.println(Thread.currentThread().getName() + "productor:我是大帅哥，我现在正在生产东西！,count:" + num);
				// 发送消息
				messageProducer.send(msg);
				// 提交事务
				this.session.commit();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
