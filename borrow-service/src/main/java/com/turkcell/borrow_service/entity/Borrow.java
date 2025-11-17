package com.turkcell.borrow_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "borrows")
public class Borrow {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "customer_id", nullable = false, updatable = false)
	private UUID customerId;

	@Column(name = "book_copy_id", nullable = false, updatable = false)
	private UUID bookCopyId;

	@Column(name = "borrow_date", nullable = false)
	private LocalDate borrowDate;

	@Column(name = "due_date", nullable = false)
	private LocalDate dueDate;

	@Column(name = "return_date")
	private LocalDate returnDate;
}