package com.bank.accounts.client;

import com.bank.accounts.dto.CardDTO;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cards", fallback = CardsFallBack.class)
public interface CardsFeignClient {

    @GetMapping(value = "/card/v1/get-user-by-mobile", consumes = "application/json")
    public ResponseEntity<CardDTO> getCardByMobileNumber(@RequestHeader("easybank-correlation-id") String correlationId,
                                                         @RequestParam String mobileNumber);

}
