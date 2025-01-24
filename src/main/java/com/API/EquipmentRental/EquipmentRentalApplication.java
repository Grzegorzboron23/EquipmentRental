package com.API.EquipmentRental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EquipmentRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentRentalApplication.class, args);
    }

}
