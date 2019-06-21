package com.capgemini.amqp.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.amqp.config.ApplicationConfigReader;
import com.capgemini.amqp.models.UserDetails;
import com.capgemini.amqp.sender.MessageSender;
import com.capgemini.amqp.util.ApplicationConstantUtils;

/**
 * @author sarjaisw
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
	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMessage(@RequestBody final UserDetails user) {
		final String exchange = getApplicationConfig().getApp1Exchange();
		final String routingKey = getApplicationConfig().getApp1RoutingKey();
		/* Sending to Message Queue */
		try {
			messageSender.sendMessage(rabbitTemplate, exchange, routingKey, user);
			return new ResponseEntity<String>(ApplicationConstantUtils.IN_QUEUE, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return new ResponseEntity<>(ApplicationConstantUtils.MESSAGE_QUEUE_SEND_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
