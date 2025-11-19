package com.turkcell.fine_service.entity;

import com.turkcell.fine_service.entity.enums.FineType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fines")
public class Fine {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "borrow_id", nullable = false, updatable = false)
	private UUID borrowId;

	@Enumerated(EnumType.STRING)
	@Column(name = "fine_type", nullable = false)
	private FineType fineType;

	@Column(name = "amount", nullable = false, precision = 12, scale = 2)
	private BigDecimal amount;

	@Column(name = "issue_date", nullable = false)
	private LocalDate issueDate;

	@Column(name = "payment_date")
	private LocalDate paymentDate;

	@Column(name = "is_paid", nullable = false)
	private boolean paid;
}

