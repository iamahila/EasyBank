package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CustomerInfoDTO {

    @Schema(
            description = "Customer personal Details"
    )
    private CustomerDTO customerDTO;

    @Schema(
            description = "Customer card details"
    )
    private CardDTO cardDTO;

    @Schema(
            description = "Customer Loan Details"
    )
    private LoanDTO loanDTO;

}
