package com.turkcell.customer_service.entity;

import java.time.LocalDateTime;

import com.turkcell.customer_service.entity.enums.MemberStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String phone;
	private String email;
	private MemberStatus memberStatus;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime registerDateTime;

	// @OneToMany(mappedBy = "customer")
	// private List<Review> reviews;

	// @OneToMany(mappedBy = "customer")
	// private List<Borrow> borrows;

	// @OneToMany(mappedBy = "customer")
	// private List<Reservation> reservations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegisterDate() {
		return registerDateTime;
	}

	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}

	// public List<Review> getReviews() {
	// return reviews;
	// }

	// public void setReviews(List<Review> reviews) {
	// this.reviews = reviews;
	// }

	// public List<Borrow> getBorrows() {
	// return borrows;
	// }

	// public void setBorrows(List<Borrow> borrows) {
	// this.borrows = borrows;
	// }

	public MemberStatus getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(MemberStatus memberStatus) {
		this.memberStatus = memberStatus;
	}

	// public List<Reservation> getReservations() {
	// return reservations;
	// }

	// public void setReservations(List<Reservation> reservations) {
	// this.reservations = reservations;
	// }

}