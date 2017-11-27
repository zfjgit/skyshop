/**
 * 
 */
package com.sitv.skyshop.amqp.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

/**
 * 
 * @author zfj20 @ 2017年9月20日
 */
@Configuration
@EnableAutoConfiguration
public class AmqpConfig {

	private static final Logger log = LoggerFactory.getLogger(AmqpConfig.class);
	
//	public static final String QUEUE_NAME = "skyshop-sync-queue";
//	public static final String QUEUE_EXCHANGE_NAME = "skyshop-sync-exchange";
//	public static final String QUEUE_ROUTING_KEY = "";
	
	@Autowired
	private Environment env;
	
	@Bean("connectionFactory")
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("rabbitmq.host"), env.getProperty("rabbitmq.port", Integer.class));

		log.info("username=" + env.getProperty("rabbitmq.username"));
		log.info("password=" + env.getProperty("rabbitmq.password"));
		log.info("virtualhost=" + env.getProperty("rabbitmq.virtualhost"));
		
		connectionFactory.setUsername(env.getProperty("rabbitmq.username"));
		connectionFactory.setPassword(env.getProperty("rabbitmq.password"));
		connectionFactory.setVirtualHost(env.getProperty("rabbitmq.virtualhost"));
//		connectionFactory.setPublisherConfirms(true); // 必须要设置
		return connectionFactory;
	}

	@Bean("amqpAdmin")
	public AmqpAdmin amqpAdmin(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) throws Exception {
		AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);
		return amqpAdmin;
	}
	
	@Bean
	public Queue queue(AmqpAdmin amqpAdmin) {
		Queue queue = new Queue(env.getProperty("rabbitmq.queuename"), true, false, false);
		queue.setAdminsThatShouldDeclare(amqpAdmin);
		return queue;
	}

	@Bean
	public TopicExchange exchange(AmqpAdmin amqpAdmin) {
		TopicExchange topicExchange = new TopicExchange(env.getProperty("rabbitmq.exchangename"), true, false);
		topicExchange.setAdminsThatShouldDeclare(amqpAdmin);
		return topicExchange;
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange, AmqpAdmin amqpAdmin) {
		Binding binding = BindingBuilder.bind(queue).to(exchange).with(env.getProperty("rabbitmq.routingkey"));
		binding.setAdminsThatShouldDeclare(amqpAdmin);
		return binding;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) throws Exception {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setChannelTransacted(true);
		return rabbitTemplate;
	}

	@Bean
	public SimpleMessageListenerContainer container(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(env.getProperty("rabbitmq.queuename"));
		container.setPrefetchCount(5);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		return container;
	}


}
