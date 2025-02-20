package com.easybank.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/customer-support")
    public Mono<String> getFallBack(){
        return Mono.just("We are facing error in Microservice, will get back to you soon!");
    }

}
