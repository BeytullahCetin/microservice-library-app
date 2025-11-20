package com.turkcell.book_service.persistence.publisher;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "publishers")
@Getter
@Setter
public class PublisherJpaEntity {
	@Id
	private UUID id;
	private String name;
	private String address;
}
