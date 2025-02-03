package com.bank.card.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.*;

@ConfigurationProperties(prefix = "cards")
public record CardCustomerDTO(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
