package com.bank.card.business;

import com.bank.card.constants.CardsConstants;
import com.bank.card.dto.CardDTO;
import com.bank.card.entity.CardEntity;
import com.bank.card.exception.CardAlreadyExistsException;
import com.bank.card.exception.ResourceNotFoundException;
import com.bank.card.mapper.CardMapper;
import com.bank.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
public class CardBusiness {

    @Autowired
    private CardService cardService;

    public void createCard(String mobileNumber){
        Optional<CardEntity> cardEntity = cardService.findByMobileNumber(mobileNumber);
        if(cardEntity.isPresent())
            throw new CardAlreadyExistsException("Card Already Exists for given mobile number");
        CardEntity newCard = createAccountEntity(mobileNumber);
        cardService.save(newCard);
    }

    public CardDTO getCustomerData(String mobileNumber){
        CardEntity cardEntity = cardService.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
                );
        return CardMapper.mapToCardDto(cardEntity, new CardDTO());
    }

    public boolean updateCard(CardDTO cardDTO){
        CardEntity cardEntity = cardService.findByCardNumber(cardDTO.getCardNumber())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Card", "card number", cardDTO.getCardNumber())
                );
        CardEntity updatedCard = CardMapper.mapToCardEntity(cardDTO, cardEntity);
        cardService.save(updatedCard);
        return true;
    }

    public boolean deleteCard(String mobileNumber){
        CardEntity cardEntity = cardService.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
                );
        cardService.deleteById(cardEntity.getCardId());
        return true;
    }

    private CardEntity createAccountEntity(String mobileNumber) {

        CardEntity newCard = new CardEntity();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

}
