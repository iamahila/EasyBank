package com.easybank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(p-> p.path("/easybank/accounts/**")
						.filters(f-> f.rewritePath("/easybank/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-response-header", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p-> p.path("/easybank/cards/**")
						.filters(f-> f.rewritePath("/easybank/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-response-header", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.route(p-> p.path("/easybank/loans/**")
						.filters(f-> f.rewritePath("/easybank/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-response-header", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))

				.build();
	}
}
