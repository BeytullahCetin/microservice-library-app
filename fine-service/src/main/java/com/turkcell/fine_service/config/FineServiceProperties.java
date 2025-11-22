package com.turkcell.fine_service.config;

import java.math.BigDecimal;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fine-service")
public class FineServiceProperties {

	private final RemoteValidation remoteValidation = new RemoteValidation();
	private final Overdue overdue = new Overdue();

	public RemoteValidation getRemoteValidation() {
		return remoteValidation;
	}

	public Overdue getOverdue() {
		return overdue;
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

	public static class Overdue {

		private BigDecimal ratePerDay = BigDecimal.valueOf(5);

		public BigDecimal getRatePerDay() {
			return ratePerDay;
		}

		public void setRatePerDay(BigDecimal ratePerDay) {
			this.ratePerDay = ratePerDay;
		}
	}
}

