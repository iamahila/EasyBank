package com.bank.accounts.controller;

import com.bank.accounts.business.AccountBusiness;
import com.bank.accounts.dto.CustomerDTO;
import com.bank.accounts.dto.CustomerSupportDTO;
import com.bank.accounts.dto.ResponseDTO;
import com.bank.accounts.service.AccountService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Fallback;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@Tag(
        name = "Account REST API",
        description = "CRUD operation for Account"
)
@RestController
@RequestMapping(path = "/account/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    @Autowired
    private AccountBusiness accountBusiness;

    @Autowired
    private CustomerSupportDTO customerSupportDTO;

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Operation(
            description = "Create Account using customer DTO",
            summary = "Create Account"

    )
    //apiresponse also have content annotation
    @ApiResponse(
            responseCode = "201",
            description = "Successfully Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO){
        accountBusiness.createAccount(customerDTO);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.CREATED, "Successfully Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            description = "Fetch Account using customer mobile number",
            summary = "Fetch Account Details"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @GetMapping("/get-user-by-mobile")
    public ResponseEntity<CustomerDTO> getCustomerByMobileNumber(@RequestParam
                                                                     @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                                     String mobileNumber){
        CustomerDTO customerDTO = accountBusiness.getCustomerData(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDTO);
    }

    @Operation(
            description = "update Account using customer DTO",
            summary = "Update Account"

    )
    @ApiResponse(
            responseCode = "202",
            description = "Successfully Updated"
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCustomerAndAccount(@Valid @RequestBody CustomerDTO customerDTO){
        boolean isUpdated = accountBusiness.updateData(customerDTO);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.ACCEPTED, "Updated: "+isUpdated);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(responseDTO);
    }

    @Operation(
            description = "Delete Account using customer mobile number",
            summary = "Delete Account"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCustomerAndAccount(@RequestParam
                                                                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Not a valid mobile number")
                                                                    String mobileNumber){
        boolean isDeleted = accountBusiness.deleteData(mobileNumber);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "Deleted: "+isDeleted);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseDTO);
    }

    @Operation(
            description = "fetch Account customer support information",
            summary = "fetch customer support"

    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status OK"
    )
    @Retry(name =   "accountsupport", fallbackMethod = "getCustomerSupportFallBack")
    @GetMapping("/account-support-info")
    public ResponseEntity<CustomerSupportDTO> getCustomerSupport() throws TimeoutException {
        logger.debug("Inside account customer support");
        throw new TimeoutException();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(customerSupportDTO);
    }

    public ResponseEntity<CustomerSupportDTO> getCustomerSupportFallBack(Throwable throwable){
        logger.debug("Inside account customer fallback");
        CustomerSupportDTO customerSupportDTO1 = new CustomerSupportDTO();
        customerSupportDTO1.setMessage("Fall back message");
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerSupportDTO1);
    }

    @RateLimiter(name = "buildRateLimit", fallbackMethod = "getBuildVersionFallBack")
    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion(){
        String value = "Build version from Actual Method is : 1.0";
        return ResponseEntity.status(HttpStatus.OK)
                .body(value);
    }

    public ResponseEntity<String> getBuildVersionFallBack(Throwable throwable){
        String value = "Build version from Fallback Method is : 1.1";
        return ResponseEntity.status(HttpStatus.OK)
                .body(value);
    }

}
