package com.sanal.omdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sanal.omdb.principal.Principal;
// import com.sanal.omdb.services.ConsumoGPT;

@SpringBootApplication
public class OmdbApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OmdbApplication.class, args);
		Principal principal = new Principal();

		principal.iniciarAplicacao();
		// ConsumoGPT consumoGPT = new ConsumoGPT();
		// consumoGPT.obterTraducao("You exceeded your current quota, please check your plan and billing details.");
	}
}
