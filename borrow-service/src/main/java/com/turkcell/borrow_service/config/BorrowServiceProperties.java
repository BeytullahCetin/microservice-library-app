package com.turkcell.borrow_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration holder for borrow-service specific settings.
 */
@ConfigurationProperties(prefix = "borrow-service")
public class BorrowServiceProperties {

	private final RemoteValidation remoteValidation = new RemoteValidation();

	public RemoteValidation getRemoteValidation() {
		return remoteValidation;
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
}

