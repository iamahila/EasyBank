package com.bank.card.mapper;

import com.bank.card.dto.CardDTO;
import com.bank.card.entity.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardMapper {

    public static CardDTO mapToCardDto(CardEntity cardEntity, CardDTO cardDTO) {
        cardDTO.setMobileNumber(cardEntity.getMobileNumber());
        cardDTO.setCardNumber(cardEntity.getCardNumber());
        cardDTO.setCardType(cardEntity.getCardType());
        cardDTO.setTotalLimit(cardEntity.getTotalLimit());
        cardDTO.setAmountUsed(cardEntity.getAmountUsed());
        cardDTO.setAvailableAmount(cardEntity.getAvailableAmount());
        return cardDTO;
    }

    public static CardEntity mapToCardEntity(CardDTO cardDTO, CardEntity cardEntity) {
        cardEntity.setMobileNumber(cardDTO.getMobileNumber());
        cardEntity.setCardNumber(cardDTO.getCardNumber());
        cardEntity.setCardType(cardDTO.getCardType());
        cardEntity.setTotalLimit(cardDTO.getTotalLimit());
        cardEntity.setAmountUsed(cardDTO.getAmountUsed());
        cardEntity.setAvailableAmount(cardDTO.getAvailableAmount());
        return cardEntity;
    }

}
