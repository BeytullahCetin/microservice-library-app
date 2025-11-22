package com.turkcell.fine_service.messaging.processor;

import com.turkcell.fine_service.config.FineServiceProperties;
import com.turkcell.fine_service.entity.Fine;
import com.turkcell.fine_service.entity.enums.FineType;
import com.turkcell.fine_service.messaging.event.BorrowOverdueEvent;
import com.turkcell.fine_service.repository.FineRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class BorrowOverdueFineProcessor {

	private final FineRepository fineRepository;
	private final FineServiceProperties fineServiceProperties;

	@Transactional
	public void process(BorrowOverdueEvent event) {
		boolean alreadyExists = fineRepository.existsByBorrowIdAndFineTypeAndPaidFalse(event.borrowId(), FineType.LATE_RETURN);
		if (alreadyExists) {
			log.info("Skipping fine creation; late return fine already exists for borrowId={}", event.borrowId());
			return;
		}

		BigDecimal ratePerDay = fineServiceProperties.getOverdue().getRatePerDay();
		if (ratePerDay == null || ratePerDay.signum() <= 0) {
			ratePerDay = BigDecimal.valueOf(5);
		}

		BigDecimal amount = ratePerDay.multiply(BigDecimal.valueOf(event.daysOverdue()));
		if (amount.signum() <= 0) {
			log.info("Skipping fine creation; non-positive amount computed for borrowId={}", event.borrowId());
			return;
		}

		Fine fine = Fine.builder()
				.borrowId(event.borrowId())
				.fineType(FineType.LATE_RETURN)
				.amount(amount)
				.issueDate(event.returnDate())
				.paid(false)
				.build();
		fineRepository.save(fine);
		log.info("Created automatic late return fine for borrowId={} amount={}", event.borrowId(), amount);
	}
}

