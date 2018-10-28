package com.nurettin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerTracer;

@SpringBootApplication
public class OpenTracingExampleApplication {

	@Bean
	public JaegerTracer jaegerTracer() {
		return new Configuration("spring-boot").getTracer(); 
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OpenTracingExampleApplication.class, args);
	}
}
