package com.bank.loan;

import com.bank.loan.dto.LoanCustomerSupportDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoanCustomerSupportDTO.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Loan Service",
				description = "Loan CRUD operations Microservice",
				summary = "Loan CRUD operations",
				contact = @Contact(
						name = "Ahila Chokkalingam",
						email = "ahilacse1995@gmail.com"
				),
				version = "v1"
		)
)
public class LoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}

}
