package com.clanone.onedayclan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OnedayclanApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnedayclanApplication.class, args);
    }

}
