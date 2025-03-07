package com.easybank.message.function;

import com.easybank.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email(){
        return accountMsgDTO -> {
            log.info("Sending email: "+ accountMsgDTO.toString());
            return accountMsgDTO;
        };
    }

    @Bean
    public Function<AccountsMsgDto, Long> sms(){
        return accountMsgDTO -> {
            log.info("Sending sms: "+ accountMsgDTO.toString());
            return accountMsgDTO.accountNumber();
        };
    }
}
