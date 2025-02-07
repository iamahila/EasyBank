package com.bank.card.exception;

public class CardAlreadyExistsException extends  RuntimeException{

    public CardAlreadyExistsException(String ex){
        super(ex);
    }

}
