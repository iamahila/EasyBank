package com.bank.accounts.client;

import com.bank.accounts.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/card/v1/get-user-by-mobile", consumes = "application/json")
    public ResponseEntity<CardDTO> getCardByMobileNumber(@RequestParam String mobileNumber);

}
