package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Loan",
        description = "Holds loan information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    @Schema(
            description = "Mobile Number info",
            example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
    @NotEmpty(message = "Mobile number cannot be empty")
    private String mobileNumber;

    @Schema(
            description = "Loan Number info",
            example = "100646930341"
    )
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Not a valid loan number")
    @NotEmpty(message = "Loan number cannot be empty")
    private String loanNumber;

    @Schema(
            description = "Loan Type info",
            example = "Land Loan"
    )

    @NotEmpty(message = "Loan type cannot be empty")
    private String loanType;

    @Schema(
            description = "Loan total info",
            example = "50000"
    )

    @Positive(message = "Total loan limit should be greater than zero")
    private int totalLoan;

    @Schema(
            description = "Amount paid info",
            example = "25000"
    )

    @PositiveOrZero(message = "Total amount paid should be equal or greater than zero")
    private int amountPaid;

    @Schema(
            description = "Outstanding Amount info",
            example = "25000"
    )

    @PositiveOrZero(message = "Outstanding amount should be equal or greater than zero")
    private int outstandingAmount;
}
