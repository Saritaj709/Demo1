package com.capgemini.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author sarjaisw
 *
 */
@SpringBootApplication
public class SpringAmqpApplication{

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(SpringAmqpApplication.class, args);
	}

	/**
	 * @param application
	 * @return
	 */
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(SpringAmqpApplication.class);
	}
}