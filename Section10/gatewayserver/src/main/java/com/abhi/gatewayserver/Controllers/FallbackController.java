package com.abhi.gatewayserver.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/contactSupport")
    public Mono<String> getSupport(){
        return Mono.just("An error occurred. Please try again or contact support team");
    }

}
