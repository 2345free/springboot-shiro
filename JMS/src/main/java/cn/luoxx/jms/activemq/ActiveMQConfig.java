package cn.luoxx.jms.activemq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
public class ActiveMQConfig {

	@Value("${spring.activemq.user}")
	private String USERNAME;

	@Value("${spring.activemq.password}")
	private String PASSWORD;

	@Value("${spring.activemq.broker-url}")
	private String BROKEN_URL;

	@Bean
	public DefaultJmsListenerContainerFactory myFactory(DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, this.connectionFactory());
		return factory;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(this.USERNAME, this.PASSWORD, this.BROKEN_URL);
	}

}
