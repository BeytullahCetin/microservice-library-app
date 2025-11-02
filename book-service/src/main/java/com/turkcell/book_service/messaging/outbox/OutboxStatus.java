package com.turkcell.book_service.messaging.outbox;

public enum OutboxStatus {
	PENDING,
	SENT,
	FAILED
}
