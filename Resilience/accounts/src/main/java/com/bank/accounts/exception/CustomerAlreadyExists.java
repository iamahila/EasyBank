package com.bank.accounts.exception;

public class CustomerAlreadyExists extends  RuntimeException{

    public CustomerAlreadyExists(String ex){
        super(ex);
    }

}
