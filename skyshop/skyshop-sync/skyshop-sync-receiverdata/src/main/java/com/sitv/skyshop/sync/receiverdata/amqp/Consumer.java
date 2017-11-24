/**
 * 
 */
package com.sitv.skyshop.sync.receiverdata.amqp;


import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.sitv.skyshop.sync.receiverdata.service.IUpdateService;


/**
 * 
 * @author zfj20 @ 2017年9月20日
 */
@Component
public class Consumer extends MessageListenerAdapter {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	private IUpdateService mysqlService;
	
	public void onMessage(Message message, Channel channel) throws Exception {
		String msg = new String(message.getBody(), "UTF-8");
		
		receiveMessage(msg);
		
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	}
	
//	@RabbitHandler
//	@RabbitListener(bindings = @QueueBinding(key = AmqpConfig.QUEUE_ROUTING_KEY, exchange = @Exchange(type = ExchangeTypes.TOPIC, value = AmqpConfig.QUEUE_EXCHANGE_NAME, durable = "true"), value = @Queue(value = AmqpConfig.QUEUE_NAME, durable = "true")), admin = "amqpAdmin")
	public void receiveMessage(String msg) {
		try {
			log.info("msg=" + msg);
			
			//amqpAdmin.
			
			if(StringUtils.isBlank(msg)) {
				return;
			}
			
			JSONObject sqlObject = new JSONObject(msg);
			
			mysqlService.update(sqlObject);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
