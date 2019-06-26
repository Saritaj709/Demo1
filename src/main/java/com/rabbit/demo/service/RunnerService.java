package com.rabbit.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Component
public class RunnerService implements MessageService<String> {

	private final RabbitTemplate rabbitTemplate;
	private final ReceiverService receiver;

	public RunnerService(ReceiverService receiver, RabbitTemplate rabbitTemplate) {
		this.receiver = receiver;
		this.rabbitTemplate = rabbitTemplate;
	}
	
	@Override
	public void sendMessage(final String exchange, final String routingKey,String message) throws InterruptedException {
		System.out.println("Sending message...");
		JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);
		rabbitTemplate.convertAndSend(exchange, routingKey, jsonObject.toString());
		System.out.println("message sent through "+exchange);
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		receiver.receivedFrom(exchange);
	}

}