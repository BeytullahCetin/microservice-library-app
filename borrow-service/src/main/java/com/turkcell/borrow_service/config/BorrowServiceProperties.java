package com.turkcell.borrow_service.config;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration holder for borrow-service specific settings.
 */
@ConfigurationProperties(prefix = "borrow-service")
public class BorrowServiceProperties {

	private final RemoteValidation remoteValidation = new RemoteValidation();
	private final Reservation reservation = new Reservation();

	public RemoteValidation getRemoteValidation() {
		return remoteValidation;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public static class RemoteValidation {
		/**
		 * When true, Borrow service will call dependent services to validate reference
		 * identifiers (customer/book/book copy).
		 */
		private boolean enabled = false;

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}

	public static class Reservation {

		/**
		 * Time to keep an auto-created reservation before expiring it if the customer
		 * does not pick up the book copy.
		 */
		private Duration autoHoldTtl = Duration.ofHours(24);

		public Duration getAutoHoldTtl() {
			return autoHoldTtl;
		}

		public void setAutoHoldTtl(Duration autoHoldTtl) {
			this.autoHoldTtl = autoHoldTtl;
		}
	}
}

