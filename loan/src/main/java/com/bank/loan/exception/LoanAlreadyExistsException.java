package com.bank.loan.exception;

public class LoanAlreadyExistsException extends  RuntimeException{

    public LoanAlreadyExistsException(String ex){
        super(ex);
    }

}
