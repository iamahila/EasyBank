package com.bank.accounts.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String entity, String resource, String value){
        super(String.format("%s not found for given %s : %s", entity, resource, value));
    }

}
