package com.turkcell.borrow_service.messaging.publisher;

import com.turkcell.borrow_service.messaging.event.BorrowOverdueEvent;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BorrowOverdueEventPublisher {

	private static final String BINDING_NAME = "borrowOverdue-out-0";

	private final StreamBridge streamBridge;

	public void publish(BorrowOverdueEvent event) {
		Message<BorrowOverdueEvent> message = MessageBuilder.withPayload(Objects.requireNonNull(event)).build();
		boolean sent = streamBridge.send(BINDING_NAME, message);
		if (!sent) {
			log.warn("Failed to publish BorrowOverdueEvent for borrowId={}", event.borrowId());
		} else {
			log.info("Published BorrowOverdueEvent for borrowId={} daysOverdue={}", event.borrowId(), event.daysOverdue());
		}
	}
}

