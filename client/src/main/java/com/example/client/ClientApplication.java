package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}

@Controller
@ResponseBody
class ClientController {

    private final RestClient http;

    ClientController(RestClient.Builder http) {
        this.http = http.build();
    }

    @GetMapping("/client")
    String client(@RegisteredOAuth2AuthorizedClient("spring") OAuth2AuthorizedClient jwt) {
        return http.get()
                .uri("http://localhost:8081/message")
                .headers(h -> h.setBearerAuth(jwt.getAccessToken().getTokenValue()))
                .retrieve()
                .body(String.class);
    }
}