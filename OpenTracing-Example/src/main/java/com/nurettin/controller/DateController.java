package com.nurettin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DateController {
	
	static final String FROM = "Eindhoven | ";
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/date")
	public String getDate() {
		return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	}
	
	@GetMapping("/hello")
	public String helloFrom() {
		return FROM+restTemplate.getForEntity("http://localhost:8080/date", String.class).getBody();
	}

}
