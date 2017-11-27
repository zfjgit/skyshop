/**
 * 
 */
package com.sitv.skyshop.amqp.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zfj20 @ 2017年9月20日
 */
@Component
public class Sender {

	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private Environment env;
	
	public void send(String msg) {
		amqpTemplate.convertAndSend(env.getProperty("rabbitmq.exchangename"), env.getProperty("rabbitmq.routingkey"), msg);
	}
}
