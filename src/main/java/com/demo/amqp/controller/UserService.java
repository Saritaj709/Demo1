package com.demo.amqp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.amqp.config.ApplicationConfigReader;
import com.demo.amqp.sender.MessageSender;
import com.demo.amqp.util.ApplicationConstantUtils;

/**
 * @author sarita
 *
 */
@RestController
@RequestMapping(path = "/userservice")
public class UserService {
	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	/**
	 * 
	 */
	private transient final RabbitTemplate rabbitTemplate;
	/**
	 * 
	 */
	private ApplicationConfigReader applicationConfig;
	/**
	 * 
	 */
	private MessageSender messageSender;

	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	/**
	 * @param applicationConfig
	 */
	@Autowired
	public void setApplicationConfig(final ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	/**
	 * @constructor UserService
	 * @param rabbitTemplate
	 */
	@Autowired
	public UserService(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	/**
	 * @param messageSender
	 */
	@Autowired
	public void setMessageSender(final MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(path = "/amqp/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMessageAsString(@RequestBody final String userDetails,
			@RequestParam(value = "exchange", required = false) String exchange,
			@RequestParam(value = "routingKey", required = false) String routingKey) {
		/* Sending to Message Queue */

		try {
			// JsonObject jsonObject = new Gson().fromJson(userDetails, JsonObject.class);
			// messageSender.sendMessage(rabbitTemplate, exchange, routingKey,
			// jsonObject.toString());

			messageSender.sendMessage(rabbitTemplate, exchange, routingKey, userDetails);

			return new ResponseEntity<>(ApplicationConstantUtils.IN_QUEUE, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return new ResponseEntity<>(ApplicationConstantUtils.MESSAGE_QUEUE_SEND_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
