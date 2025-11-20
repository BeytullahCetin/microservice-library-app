package com.turkcell.book_service.domain.publisher.model;

public class Publisher {
	private final PublisherId id;
	private String name;
	private String address;

	private Publisher(PublisherId id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public static Publisher create(String name, String address) {
		validateName(name);
		validateAddress(address);

		return new Publisher(PublisherId.generate(), name, address);
	}

	public static Publisher rehydrate(PublisherId id, String name, String address) {
		return new Publisher(id, name, address);
	}

	private static void validateName(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name cannot be null or empty");

		if (name.length() >= 255)
			throw new IllegalArgumentException("Name length must be less than 255 characters");
	}

	private static void validateAddress(String address) {
		if (address == null || address.isEmpty())
			throw new IllegalArgumentException("Address cannot be null or empty");

		if (address.length() >= 255)
			throw new IllegalArgumentException("Address length must be less than 255 characters");
	}

	public void rename(String name) {
		validateName(name);
		this.name = name;
	}

	public void changeAddress(String address) {
		validateAddress(address);
		this.address = address;
	}

	public PublisherId getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
}
