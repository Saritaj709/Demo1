package com.rabbit.demo.service;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

@Component
public class ReceiverService {

	    private CountDownLatch latch = new CountDownLatch(1);

	    public void receiveMessage(String message) {
	    	System.out.println("Received <" + message + ">");
	        latch.countDown();
	    }

	    public CountDownLatch getLatch() {
	        return latch;
	    }
	    
	    public void receivedFrom(String exchange) {
	    	System.out.println("Message received from "+exchange);
	    }

	}
