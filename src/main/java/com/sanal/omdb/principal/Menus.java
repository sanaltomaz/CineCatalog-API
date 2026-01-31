package com.sanal.omdb.principal;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.sanal.omdb.models.Serie;
import com.sanal.omdb.services.SerieService;

@Component
public class Menus {

    private final SerieService serieService;
    private final Scanner scanner = new Scanner(System.in);

    public Menus(SerieService serieService) {
        this.serieService = serieService;
    }

    public void iniciarMenu() {
        System.out.print("Nome da série: ");
        String nome = scanner.nextLine();

        Serie serie = serieService.buscarSerieComEpisodios(nome);
        System.out.println(serie);
    }
}

