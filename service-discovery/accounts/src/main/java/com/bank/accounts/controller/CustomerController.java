package com.bank.accounts.controller;

import com.bank.accounts.business.CustomerBusiness;
import com.bank.accounts.dto.CustomerInfoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/")
@AllArgsConstructor
public class CustomerController {

    private CustomerBusiness customerBusiness;

    @GetMapping("customer-info")
    public ResponseEntity<CustomerInfoDTO> getCustomerInfo(@RequestParam String mobileNumber){
        CustomerInfoDTO customerInfoDTO = customerBusiness.getCustomerInfo(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerInfoDTO);
    }

}
