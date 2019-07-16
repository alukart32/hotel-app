package ru.relex.hotelteam.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.relex")
public class HotelApp {
    public static void main(String[] args) {
        SpringApplication.run(HotelApp.class, args);
    }
}
