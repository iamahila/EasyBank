package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Account",
        description = "Holds account information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @Schema(
            description = "Account Number info",
            example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
    @NotEmpty(message = "Account number cannot be empty")
    private Long accountNumber;

    @Schema(
            description = "Account Type info",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @Schema(
            description = "Branch address info",
            example = "ABC"
    )
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;

    @Schema(
            description = "Customer Id info"
    )
    private Long customerId;
}
