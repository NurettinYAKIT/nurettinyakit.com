package com.nurettin.controller;

import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nurettin.SpringRabbitMqApplication;
import com.nurettin.mq.Receiver;

@RestController
public class MessageController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private Receiver receiver;

	@PostMapping("/messages")
	public String sendMessage(@Valid @RequestBody String message) {

		rabbitTemplate.convertAndSend(SpringRabbitMqApplication.topicExchangeName, "foo.bar.baz", message);
		try {
			receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return "Message added to the queue : " + message;
	}

}
