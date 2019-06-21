package com.capgemini.amqp.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Message sender to send message to queue using exchange.
 */
/**
 * @author sarjaisw
 *
 */
@Component
public class MessageSender {
	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageSender.class);

	/**
	 * 
	 * @param rabbitTemplate
	 * @param exchange
	 * @param routingKey
	 * @param data
	 */
	public void sendMessage(final RabbitTemplate rabbitTemplate,final String exchange,final String routingKey,final Object data) {
		LOGGER.info("Sending message to the queue using routingKey {}. Message= {}", routingKey, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data);
		LOGGER.info("The message has been sent to the queue.");
	}

	/**
	 * @param rabbitTemplate
	 * @param exchange
	 * @param routingKey
	 * @param url
	 * @param data
	 */
	public void sendMessagePublicCorpus(final RabbitTemplate rabbitTemplate,final String exchange,final String routingKey,final String url,
			final Object data) {
		LOGGER.info("Sending message to the queue using routingKey {}.Url={}. Message= {}", routingKey, url, data);
		rabbitTemplate.convertAndSend(exchange, routingKey, data);
		LOGGER.info("The message has been sent to the queue.");
	}

}
