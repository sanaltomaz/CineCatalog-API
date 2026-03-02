package com.sanal.omdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OmdbApplication {

    public static void main(String[] args) {
        ApplicationContext context =
            SpringApplication.run(OmdbApplication.class, args);

        System.out.println("Aplicação iniciada com sucesso!");
    }
}

