package com.bank.loan.repository;

import com.bank.loan.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    Optional<LoanEntity> findByMobileNumber(String mobileNumber);

    Optional<LoanEntity> findByLoanNumber(String loanNumber);

}
