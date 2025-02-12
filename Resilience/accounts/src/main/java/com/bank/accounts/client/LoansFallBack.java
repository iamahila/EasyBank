package com.bank.accounts.client;

import com.bank.accounts.dto.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements LoanFeignClient{
    @Override
    public ResponseEntity<LoanDTO> getLoanByMobileNumber(String correlationId, String mobileNumber) {
        return null;
    }
}
