package com.turkcell.fine_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fine-service")
public class FineServiceProperties {

	private final RemoteValidation remoteValidation = new RemoteValidation();

	public RemoteValidation getRemoteValidation() {
		return remoteValidation;
	}

	public static class RemoteValidation {

		private boolean enabled = false;

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}
}

