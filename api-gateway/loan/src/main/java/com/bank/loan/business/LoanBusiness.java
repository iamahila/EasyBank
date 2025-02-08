package com.bank.loan.business;

import com.bank.loan.constants.LoanConstants;
import com.bank.loan.dto.LoanDTO;
import com.bank.loan.entity.LoanEntity;
import com.bank.loan.exception.LoanAlreadyExistsException;
import com.bank.loan.exception.ResourceNotFoundException;
import com.bank.loan.mapper.LoanMapper;
import com.bank.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
public class LoanBusiness {

    @Autowired
    private LoanService loanService;

    public void createLoan(String mobileNumber){
        Optional<LoanEntity> loanEntity = loanService.findByMobileNumber(mobileNumber);
        if(loanEntity.isPresent())
            throw new LoanAlreadyExistsException("Loan Already Exists for given mobile number");
        LoanEntity newCard = createAccountEntity(mobileNumber);
        loanService.save(newCard);
    }

    public LoanDTO getCustomerData(String mobileNumber){
        LoanEntity loanEntity = loanService.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber)
                );
        return LoanMapper.mapToLoanDto(loanEntity, new LoanDTO());
    }

    public boolean updateLoan(LoanDTO loanDTO){
        LoanEntity loanEntity = loanService.findByLoanNumber(loanDTO.getLoanNumber())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Loan", "loan number", loanDTO.getLoanNumber())
                );
        LoanEntity updatedCard = LoanMapper.mapToLoanEntity(loanDTO, loanEntity);
        loanService.save(updatedCard);
        return true;
    }

    public boolean deleteLoan(String mobileNumber){
        LoanEntity cardEntity = loanService.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber)
                );
        loanService.deleteById(cardEntity.getLoanId());
        return true;
    }

    private LoanEntity createAccountEntity(String mobileNumber) {

        LoanEntity newLoan = new LoanEntity();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomCardNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(LoanConstants.AMOUNT_PAID);
        newLoan.setOutstandingAmount(LoanConstants.OUTSTANDING_AMOUNT);
        return newLoan;
    }

}
