package com.kurdev.marvel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.kurdev"})
public class MarvelApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarvelApplication.class, args);
    }
}
