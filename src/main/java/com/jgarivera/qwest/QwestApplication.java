package com.jgarivera.qwest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.modulith.Modulithic;

@Modulithic(sharedModules = "shared")
@SpringBootApplication
public class QwestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QwestApplication.class, args);
    }
}
