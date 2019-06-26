package com.rabbit.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.demo.service.RunnerService;
import com.rabbit.demo.utils.MessageConstantUtils;

/**
 * @author sarita
 *
 */
@RestController
@RequestMapping(path = "/api")
public class AMQPController {
	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AMQPController.class);
	
	@Autowired
	private RunnerService runnerService;

	/**
	 * @param userDetails
	 * @param exchange
	 * @param routingKey
	 * @return
	 */
	@RequestMapping(path = "/amqp/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMessageAsString(@RequestBody final String message,
			@RequestParam(value = "exchange", required = false) String exchange,
			@RequestParam(value = "routingKey", required = false) String routingKey) {
		/* Sending to Message Queue */

		try {
			runnerService.sendMessage(exchange,routingKey,message);

			return new ResponseEntity<>(MessageConstantUtils.IN_QUEUE, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception occurred while sending message to the queue. Exception= {}", ex);
			return new ResponseEntity<>(MessageConstantUtils.MESSAGE_QUEUE_SEND_ERROR,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
