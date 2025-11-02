package com.turkcell.borrow_service.messaging.consumer;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTestConsumer {
	@Bean
	public Consumer<KafkaTestEvent> kafkaTest() {
		return event -> {
			System.out.println("kafka test event başarıyla okundu!");
			System.out.println(event.kafkaTest);
		};
	}

	record KafkaTestEvent(String kafkaTest) {
	}
}
