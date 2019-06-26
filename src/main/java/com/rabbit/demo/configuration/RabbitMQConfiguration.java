package com.rabbit.demo.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbit.demo.service.ReceiverService;

@Configuration
public class RabbitMQConfiguration {
	@Autowired
	private ApplicationConfigReader applicationConfig;

	public ApplicationConfigReader getApplicationConfig() {
		return applicationConfig;
	}

	public void setApplicationConfig(ApplicationConfigReader applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	/* This bean is to read the properties file configs */
	@Bean
	public ApplicationConfigReader applicationConfig() {
		return new ApplicationConfigReader();
	}

	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getApp1Exchange() {
		return new TopicExchange(getApplicationConfig().getApp1Exchange());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getApp1Queue() {
		return new Queue(getApplicationConfig().getApp1Queue());
	}

	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingApp1() {
		return BindingBuilder.bind(getApp1Queue()).to(getApp1Exchange())
				.with(getApplicationConfig().getApp1RoutingKey());
	}

	/* Creating a bean for the Message queue Exchange */
	@Bean
	public TopicExchange getApp2Exchange() {
		return new TopicExchange(getApplicationConfig().getApp2Exchange());
	}

	/* Creating a bean for the Message queue */
	@Bean
	public Queue getApp2Queue() {
		return new Queue(getApplicationConfig().getApp2Queue());
	}

	/* Binding between Exchange and Queue using routing key */
	@Bean
	public Binding declareBindingApp2() {
		return BindingBuilder.bind(getApp2Queue()).to(getApp2Exchange())
				.with(getApplicationConfig().getApp2RoutingKey());
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(applicationConfig.getApp1Queue(),
				applicationConfig.getApp2Queue());

		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	public MessageListenerAdapter listenerAdapter(ReceiverService receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

}
