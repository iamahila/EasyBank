package com.bank.loan.service;

import com.bank.loan.entity.LoanEntity;
import com.bank.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Optional<LoanEntity> findByMobileNumber(String mobileNumber){
        return loanRepository.findByMobileNumber(mobileNumber);
    }

    public Optional<LoanEntity> findByLoanNumber(String loanNumber){
        return loanRepository.findByLoanNumber(loanNumber);
    }

    public void save(LoanEntity loanEntity){
        loanRepository.save(loanEntity);
    }

    public void deleteById(Long loanId){
        loanRepository.deleteById(loanId);
    }

}
