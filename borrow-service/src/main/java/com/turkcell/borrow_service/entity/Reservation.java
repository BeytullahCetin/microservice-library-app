package com.turkcell.borrow_service.entity;

import java.time.LocalDateTime;
// import java.util.Date;

import com.turkcell.borrow_service.entity.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// @ManyToOne
	// @JoinColumn(name = "customer_id")
	// private Customer customer;

	// @ManyToOne
	// @JoinColumn(name = "book_id")
	// private Book book;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime expireAt;
	private ReservationStatus reservationStatus;

	public LocalDateTime getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(LocalDateTime expireAt) {
		this.expireAt = expireAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// public Customer getCustomer() {
	// return customer;
	// }

	// public void setCustomer(Customer customer) {
	// this.customer = customer;
	// }

	// public Book getBook() {
	// return book;
	// }

	// public void setBook(Book book) {
	// this.book = book;
	// }

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

}