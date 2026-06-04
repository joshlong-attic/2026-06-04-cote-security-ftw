package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@SpringBootApplication
//@EnableMultiFactorAuthentication( authorities =  {
//        FactorGrantedAuthority.OTT_AUTHORITY ,
//        FactorGrantedAuthority.PASSWORD_AUTHORITY
//})
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    Customizer<HttpSecurity> httpSecurityCustomizer() {
        return http -> http
                .oauth2AuthorizationServer(a -> a.oidc(Customizer.withDefaults()));

//                .oneTimeTokenLogin(a -> a
//                        .tokenGenerationSuccessHandler((request, response, oneTimeToken) -> {
//
//                            response.getWriter().println("you've got console mail!");
//                            response.setContentType(MediaType.TEXT_PLAIN_VALUE);
//
//                            IO.println("please go to http://localhost:8080/login/ott?token=" + oneTimeToken.getTokenValue());
//
//                        }))
//                .webAuthn(w -> w
//                        .rpId("localhost")
//                        .rpName("bootiful")
//                        .allowedOrigins("http://localhost:8080")
//                );
    }

    @Bean
    InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        var pw = passwordEncoder.encode("pw");
        return new InMemoryUserDetailsManager(
                User.withUsername("cote").password(pw).roles("USER").build()
        );
    }

}
