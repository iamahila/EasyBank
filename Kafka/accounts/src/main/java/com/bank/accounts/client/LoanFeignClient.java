package com.bank.accounts.client;

import com.bank.accounts.dto.LoanDTO;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "loans", fallback = LoansFallBack.class)
public interface LoanFeignClient {

    @GetMapping("/loan/v1/get-user-by-mobile")
    public ResponseEntity<LoanDTO> getLoanByMobileNumber(@RequestHeader("easybank-correlation-id") String correlationId,
                                                         @RequestParam String mobileNumber);

}
