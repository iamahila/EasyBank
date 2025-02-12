package com.bank.accounts.controller;

import com.bank.accounts.business.CustomerBusiness;
import com.bank.accounts.dto.CustomerInfoDTO;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/")
public class CustomerController {

    @Autowired
    private CustomerBusiness customerBusiness;

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("customer-info")
    public ResponseEntity<CustomerInfoDTO> getCustomerInfo(
            @RequestHeader("easybank-correlation-id") String correlationId,
            @RequestParam @Pattern(regexp="(^$|[0-9]{10})",
                    message = "Mobile number must be 10 digits") String mobileNumber){
        logger.debug("correlation id in accounts controller: "+ correlationId);
        CustomerInfoDTO customerInfoDTO = customerBusiness.getCustomerInfo(correlationId, mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerInfoDTO);
    }

}
