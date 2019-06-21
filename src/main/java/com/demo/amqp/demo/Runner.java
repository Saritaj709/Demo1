package com.demo.amqp.demo;
/*
 * package com.capgemini.amqp.demo;
 * 
 * import java.util.concurrent.TimeUnit;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.amqp.rabbit.core.RabbitTemplate; import
 * org.springframework.boot.CommandLineRunner; import
 * org.springframework.stereotype.Component;
 * 
 * import com.capgemini.amqp.config.RabbitMQConfig;
 * 
 *//**
	 * @author sarita
	 *
	 */
/*
 * @Component public class Runner implements CommandLineRunner {
 * 
 *//**
	* 
	*/
/*
 * private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
 * 
 *//**
	* 
	*/
/*
 * private transient final RabbitTemplate rabbitTemplate;
 *//**
	* 
	*/
/*
 * private transient final Receiver receiver;
 * 
 *//**
	 * @param receiver
	 * @param rabbitTemplate
	 */
/*
 * public Runner(final Receiver receiver,final RabbitTemplate rabbitTemplate) {
 * this.receiver = receiver; this.rabbitTemplate = rabbitTemplate; }
 * 
 *//**
	*
	*//*
		 * @Override public void run(final String... args) throws Exception {
		 * //System.out.println("Sending message...");
		 * LOGGER.info("Waiting five seconds..."); Thread.sleep(5000);
		 * rabbitTemplate.convertAndSend(RabbitMQConfig.TOPICEXCHANGENAME,
		 * "foo.bar.baz", "Hello from RabbitMQ!"); receiver.getLatch().await(10000,
		 * TimeUnit.MILLISECONDS); Thread.sleep(5000); }
		 * 
		 * }
		 */