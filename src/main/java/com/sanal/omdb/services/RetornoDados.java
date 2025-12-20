package com.sanal.omdb.services;

import io.github.cdimascio.dotenv.Dotenv;

public class RetornoDados {

    private Dotenv dotenv = Dotenv.load();
    
    private ConsumoApi consumo = new ConsumoApi();
    private String endereco = dotenv.get("ENDERECO");
    private String apiKey = dotenv.get("API_KEY");

    public String retornarDadosTitulo(String nomeDoTitulo) {
        return consumo.obterDados(
            endereco + nomeDoTitulo.replace(" ", "+") + apiKey
        );
    }

    public String retornarDadosEpisodeos(String nomeDoTitulo, int temporada) {
        return consumo.obterDados(
            endereco + nomeDoTitulo.replace(" ", "+") +
            "&Season=" + temporada + apiKey
        );
    }
}
