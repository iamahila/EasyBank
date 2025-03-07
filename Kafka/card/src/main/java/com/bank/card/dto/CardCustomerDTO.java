package com.bank.card.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.*;

@ConfigurationProperties(prefix = "cards")
@Getter
@Setter
public class CardCustomerDTO {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
