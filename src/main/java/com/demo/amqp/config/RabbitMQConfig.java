package com.demo.amqp.config;
/*
 * package com.capgemini.amqp.config;
 * 
 * import org.springframework.amqp.core.Binding; import
 * org.springframework.amqp.core.BindingBuilder; import
 * org.springframework.amqp.core.Queue; import
 * org.springframework.amqp.core.TopicExchange; import
 * org.springframework.amqp.rabbit.annotation.EnableRabbit; import
 * org.springframework.amqp.rabbit.connection.ConnectionFactory; import
 * org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
 * import
 * org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import com.capgemini.amqp.demo.Receiver;
 * 
 * @Configuration
 * 
 * @EnableRabbit public class RabbitMQConfig { public static final String
 * TOPICEXCHANGENAME = "spring-boot-exchange";
 * 
 * public static final String QUEUENAME = "spring-boot";
 * 
 * @Bean Queue queue() { return new Queue(QUEUENAME, false); }
 * 
 * @Bean TopicExchange exchange() { return new TopicExchange(TOPICEXCHANGENAME);
 * }
 * 
 * @Bean Binding binding(Queue queue, TopicExchange exchange) { return
 * BindingBuilder.bind(queue).to(exchange).with("foo.bar.#"); }
 * 
 * @Bean SimpleMessageListenerContainer container(ConnectionFactory
 * connectionFactory, MessageListenerAdapter listenerAdapter) {
 * SimpleMessageListenerContainer container = new
 * SimpleMessageListenerContainer();
 * container.setConnectionFactory(connectionFactory);
 * container.setQueueNames(QUEUENAME);
 * container.setMessageListener(listenerAdapter); return container; }
 * 
 * @Bean MessageListenerAdapter listenerAdapter(Receiver receiver) { return new
 * MessageListenerAdapter(receiver, "receiveMessage"); }
 * 
 * }
 */