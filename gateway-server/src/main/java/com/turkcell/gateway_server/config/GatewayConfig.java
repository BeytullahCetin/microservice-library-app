
package com.turkcell.gateway_server.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("book-service", r -> r
						.path("/api/v1/books/**")
						.filters(f -> f
								.retry(config -> config.setRetries(3)))
						.uri("lb://book-service"))
				.build();
	}
}