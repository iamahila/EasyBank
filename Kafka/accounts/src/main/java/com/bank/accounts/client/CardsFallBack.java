package com.bank.accounts.client;

import com.bank.accounts.dto.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient{


    @Override
    public ResponseEntity<CardDTO> getCardByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }
}
