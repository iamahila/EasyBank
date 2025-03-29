package com.bank.card;

import com.bank.card.dto.CardCustomerDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {CardCustomerDTO.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Card microservice REST API",
				description = "Bank Application rest api implementation",
				version = "v1",
				contact = @Contact(
						name = "Ahila Chokkalingam",
						email = "ahilacse1995@gmail.com"
				),
				license = @License(
						name = "abc license"
				)
		)
)
public class CardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardApplication.class, args);
	}

}
