package com.nurettin;

import brave.Tracing;
import brave.opentracing.BraveTracer;
import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.okhttp3.OkHttpSender;


@SpringBootApplication
public class OpenTracingExampleApplication {

    @Bean
    @Profile("!zipkin")
    public Tracer jaegerTracer() {
        return new Configuration("spring-boot").getTracer();
    }

    @Bean
    @Profile("zipkin")
    public Tracer zipkinTracer() {
        OkHttpSender sender = OkHttpSender.create("http://127.0.0.1:9411/api/v2/spans");
        AsyncReporter spanReporter = AsyncReporter.create(sender);

        Tracing braveTracing = Tracing.newBuilder()
                .localServiceName("my-service")
                .spanReporter(spanReporter)
                .build();

        // use this to create an OpenTracing Tracer
        Tracer tracer = BraveTracer.create(braveTracing);

        return tracer;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenTracingExampleApplication.class, args);
    }
}
