package com.example.barotest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BaroTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaroTestApplication.class, args);
    }

}
