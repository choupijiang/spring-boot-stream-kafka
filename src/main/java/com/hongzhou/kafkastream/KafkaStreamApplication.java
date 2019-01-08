package com.hongzhou.kafkastream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.hongzhou.kafkastream.channel.AnalyticsBinding;

@SpringBootApplication
@EnableBinding(AnalyticsBinding.class)
public class KafkaStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamApplication.class, args);
	}
}

