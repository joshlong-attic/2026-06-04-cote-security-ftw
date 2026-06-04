package com.example.service;

import jakarta.servlet.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
//class MyFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        IO.println("hi");
//        chain.doFilter(request, response);
//    }
//}
//
//
@Controller
@ResponseBody
class MeController {

    @GetMapping("/message")
    Map<String, String> me() {
        var principal = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return Map.of("message", principal.getName());
    }
}
