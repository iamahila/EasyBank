package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Customer",
        description = "Holds customer information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @Schema(
            description = "Customer Name",
            example = "Ahila"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "Length of name should be between 5 to 30")
    private String name;

    @Schema(
            description = "Customer email",
            example = "ahilacse1995@gmail.com"
    )
    @NotEmpty(message ="Email address cannot be null" )
    @Email(message = "Not a valid email address")
    private String email;

    @Schema(
            description = "Customer mobile number",
            example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountDTO accountDTO;
}
