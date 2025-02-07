package com.bank.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "Card",
        description = "Holds card information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    @Schema(
            description = "Mobile Number info",
            example = "1234567890"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
    @NotEmpty(message = "Mobile number cannot be empty")
    private String mobileNumber;

    @Schema(
            description = "Card Number info",
            example = "100646930341"
    )
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Not a valid card number")
    @NotEmpty(message = "Card number cannot be empty")
    private String cardNumber;

    @Schema(
            description = "Card Type info",
            example = "Platinum"
    )

    @NotEmpty(message = "Card type cannot be empty")
    private String cardType;

    @Schema(
            description = "Card Limit info",
            example = "50000"
    )

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @Schema(
            description = "Amount used info",
            example = "25000"
    )

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    private int amountUsed;

    @Schema(
            description = "Amount available info",
            example = "25000"
    )

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private int availableAmount;
}
