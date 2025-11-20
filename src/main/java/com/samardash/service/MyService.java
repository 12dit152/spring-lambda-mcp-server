package com.samardash.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MyService {

    private final RestClient restClient;

    public MyService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("https://samardash.com").build();
    }

    public String getDetails() {
        return this.restClient.get().retrieve().body(String.class);
    }

}
