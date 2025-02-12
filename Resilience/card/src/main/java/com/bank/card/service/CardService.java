package com.bank.card.service;

import com.bank.card.entity.CardEntity;
import com.bank.card.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Optional<CardEntity> findByMobileNumber(String mobileNumber){
        return cardRepository.findByMobileNumber(mobileNumber);
    }

    public Optional<CardEntity> findByCardNumber(String cardNumber){
        return cardRepository.findByCardNumber(cardNumber);
    }

    public void save(CardEntity cardEntity){
        cardRepository.save(cardEntity);
    }

    public void deleteById(Long cardId){
        cardRepository.deleteById(cardId);
    }

}
