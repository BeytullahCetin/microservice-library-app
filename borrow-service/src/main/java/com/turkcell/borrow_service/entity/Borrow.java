package com.turkcell.borrow_service.entity;

import java.time.LocalDateTime;

// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "borrows")
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime borrDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dueDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime returnDateTime;

	// @ManyToOne
	// @JoinColumn(name = "customer_id")
	// private Customer customer;

	// @ManyToOne
	// @JoinColumn(name = "book_copy_id")
	// private BookCopy bookCopy;

	// @OneToMany(mappedBy = "borrow")
	// private List<Fine> fines;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getBorrDateTime() {
		return borrDateTime;
	}

	public void setBorrDateTime(LocalDateTime borrDateTime) {
		this.borrDateTime = borrDateTime;
	}

	public LocalDateTime getReturnDateTime() {
		return returnDateTime;
	}

	public void setReturnDateTime(LocalDateTime returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	// public Customer getCustomer() {
	// return customer;
	// }

	// public void setCustomer(Customer customer) {
	// this.customer = customer;
	// }

	// public BookCopy getBookCopy() {
	// return bookCopy;
	// }

	// public void setBookCopy(BookCopy bookCopy) {
	// this.bookCopy = bookCopy;
	// }

	// public List<Fine> getFines() {
	// return fines;
	// }

	// public void setFines(List<Fine> fines) {
	// this.fines = fines;
	// }

	public LocalDateTime getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(LocalDateTime dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

}