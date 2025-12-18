package com.sanal.omdb.principal;

import com.sanal.omdb.models.*;

import io.github.cdimascio.dotenv.Dotenv;
import com.sanal.omdb.services.ConsumoApi;


public class Funcoes {
    Dotenv dotenv = Dotenv.load();
    private ConsumoApi consumo = new ConsumoApi();

    String endereco = dotenv.get("ENDERECO");
    String apiKey = dotenv.get("API_KEY");

    public String retornarDadosTitulo(String nomeDoTitulo) {
        // Implementar a lógica para retornar dados aqui
        var json = consumo.obterDados(
            endereco + nomeDoTitulo.replace(" ", "+") + apiKey
        );
        return json;
    }

    public static void listarEpisodios(DadosSerie classe) {
        System.out.println("Listando episódios...");
        // Implementar a lógica para listar episódios aqui
        // List<DadosTemporada> temporadas = new ArrayList<>();

        //     for (int i = 1; i <= classe.getTemporadas(); i++) {
        //         json = consumo.obterDados(
        //             endereco + nomeDoFilme.replace(" ", "+") + "&season=" + i + apiKey
        //         );
        //         DadosTemporada dadosTemporada = converte.obterDados(json, DadosTemporada.class);
        //         temporadas.add(dadosTemporada);
        //     }
        //     temporadas.forEach(System.out::println);
    }
    public void buscarEpisodioPorNumero(int numero) {
        System.out.println("Buscando episódio número: " + numero);
        // Implementar a lógica para buscar um episódio por número aqui
    }
    public void listarMelhoresEpisodios() {
        System.out.println("Listando melhores episódios...");
        // Implementar a lógica para listar os melhores episódios aqui
    }
    public void listarPioresEpisodios() {
        System.out.println("Listando piores episódios...");
        // Implementar a lógica para listar os piores episódios aqui
    }
}
