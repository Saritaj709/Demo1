package com.demo.amqp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.demo.amqp.config.ApplicationConfigReader;
import com.demo.amqp.models.UserDetails;
import com.demo.amqp.util.ApplicationConstantUtils;

/**
 * Message Listener for RabbitMQ
 */
/**
 * @author sarita
 *
 */
@Service
public class MessageListener {
	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);
	/**
	 * 
	 */
	@Autowired
	private ApplicationConfigReader applicationConfigReader;

	/**
	 * Message listener for app1
	 * 
	 * @param UserDetails a user defined object used for deserialization of message
	 */
	/**
	 * @param data
	 */
	@RabbitListener(queues = "${app1.queue.name}")
	public void receiveMessageForApp1(final UserDetails data) {
		LOGGER.info("Received message: {} from app1 queue.", data);
		try {
			LOGGER.info("Making REST call to the API");
			// TODO: Code to make REST call
			LOGGER.info("<< Exiting receiveMessageForApp1() after API call.");
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				LOGGER.info("Delay...");
				try {
					Thread.sleep(ApplicationConstantUtils.MESSAGE_RETRY_DELAY);
				} catch (InterruptedException e) {
				}
				LOGGER.info("Throwing exception so that message will be requed in the queue.");
				// Note: Typically Application specific exception should be thrown below
				throw new RuntimeException("exception "+ex.getMessage());
			} else {
				throw new AmqpRejectAndDontRequeueException(ex);
			}
		} catch (Exception e) {
			LOGGER.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

	/**
	 * Message listener for app2
	 * 
	 */
	@RabbitListener(queues = "${app2.queue.name}")
	public void receiveMessageForApp2(final String reqObj) {
		LOGGER.info("Received message: {} from app2 queue.", reqObj);
		try {
			LOGGER.info("Making REST call to the API");
			// TODO: Code to make REST call
			LOGGER.info("<< Exiting receiveMessageCrawlCI() after API call.");
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
				LOGGER.info("Delay...");
				try {
					Thread.sleep(ApplicationConstantUtils.MESSAGE_RETRY_DELAY);
				} catch (InterruptedException e) {
				}
				LOGGER.info("Throwing exception so that message will be requed in the queue.");
				// Note: Typically Application specific exception can be thrown below
				throw new RuntimeException();
			} else {
				throw new AmqpRejectAndDontRequeueException(ex);
			}
		} catch (Exception e) {
			LOGGER.error("Internal server error occurred in python server. Bypassing message requeue {}", e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
}