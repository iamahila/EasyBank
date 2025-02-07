package com.bank.loan.mapper;

import com.bank.loan.dto.LoanDTO;
import com.bank.loan.entity.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanMapper {

    public static LoanDTO mapToLoanDto(LoanEntity loanEntity, LoanDTO loanDTO) {
        loanDTO.setMobileNumber(loanEntity.getMobileNumber());
        loanDTO.setLoanNumber(loanEntity.getLoanNumber());
        loanDTO.setLoanType(loanEntity.getLoanType());
        loanDTO.setTotalLoan(loanEntity.getTotalLoan());
        loanDTO.setAmountPaid(loanEntity.getAmountPaid());
        loanDTO.setOutstandingAmount(loanEntity.getOutstandingAmount());
        return loanDTO;
    }

    public static LoanEntity mapToLoanEntity(LoanDTO loanDTO, LoanEntity loanEntity) {
        loanEntity.setMobileNumber(loanDTO.getMobileNumber());
        loanEntity.setLoanNumber(loanDTO.getLoanNumber());
        loanEntity.setLoanType(loanDTO.getLoanType());
        loanEntity.setTotalLoan(loanDTO.getTotalLoan());
        loanEntity.setAmountPaid(loanDTO.getAmountPaid());
        loanEntity.setOutstandingAmount(loanDTO.getOutstandingAmount());
        return loanEntity;
    }

}
