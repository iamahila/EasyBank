package com.bank.loan.controller;

import com.bank.loan.business.LoanBusiness;
import com.bank.loan.dto.LoanDTO;
import com.bank.loan.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Loan REST API",
        description = "CRUD operation for Loan"
)
@RestController
@RequestMapping(path = "/loan/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoanController {

    @Autowired
    private LoanBusiness loanBusiness;

    @Operation(
            description = "Create loan using mobile number",
            summary = "Create loan"

    )
    //apiresponse also have content annotation
    @ApiResponse(
            responseCode = "201",
            description = "Successfully Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                         String mobileNumber){
        loanBusiness.createLoan(mobileNumber);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.CREATED, "Successfully Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            description = "Fetch loan using customer mobile number",
            summary = "Fetch loan Details"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @GetMapping("/get-user-by-mobile")
    public ResponseEntity<LoanDTO> getLoanByMobileNumber(@RequestParam
                                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                                     String mobileNumber){
        LoanDTO loanDTO = loanBusiness.getCustomerData(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(loanDTO);
    }

    @Operation(
            description = "update loan using loan DTO",
            summary = "Update Loan"

    )
    @ApiResponse(
            responseCode = "202",
            description = "Successfully Updated"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody LoanDTO loanDTO){
        boolean isUpdated = loanBusiness.updateLoan(loanDTO);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.ACCEPTED, "Updated: "+isUpdated);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(responseDTO);
    }

    @Operation(
            description = "Delete Loan using customer mobile number",
            summary = "Delete Loan"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam
                                                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                                    String mobileNumber){
        boolean isDeleted = loanBusiness.deleteLoan(mobileNumber);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "Deleted: "+isDeleted);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDTO);
    }

}
