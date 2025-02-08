package com.bank.accounts.client;

import com.bank.accounts.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoanFeignClient {

    @GetMapping("/loan/v1/get-user-by-mobile")
    public ResponseEntity<LoanDTO> getLoanByMobileNumber(@RequestParam String mobileNumber);

}
