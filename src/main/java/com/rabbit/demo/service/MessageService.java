package com.rabbit.demo.service;

public interface MessageService<T> {
//	public void sendMessage(T message) throws InterruptedException;

	void sendMessage(T exchange,T routingKey, T message) throws InterruptedException;
}
