package com.ashokit.applicationregistration.service.impl;

import com.ashokit.applicationregistration.service.EligibilityService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EligibilityServiceImpl implements EligibilityService {
    @Override
    public boolean isCitizenEligible(Long ssn) {
        WebClient webClient = WebClient.create();

        String stateName = webClient
                .get()
                .uri("https://ssa-web-api.herokuapp.com/ssn/{ssn}", ssn)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return stateName.equals("New Jersey");
    }
}
