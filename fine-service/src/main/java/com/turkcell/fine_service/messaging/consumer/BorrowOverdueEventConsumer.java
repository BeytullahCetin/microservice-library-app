package com.turkcell.fine_service.messaging.consumer;

import com.turkcell.fine_service.messaging.event.BorrowOverdueEvent;
import com.turkcell.fine_service.messaging.processor.BorrowOverdueFineProcessor;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BorrowOverdueEventConsumer {

	private final BorrowOverdueFineProcessor fineProcessor;

	@Bean
	public Consumer<BorrowOverdueEvent> borrowOverdue() {
		return fineProcessor::process;
	}
}

