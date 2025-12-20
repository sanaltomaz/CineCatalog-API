package com.sanal.omdb.principal;

import java.util.ArrayList;
import java.util.List;

import com.sanal.omdb.models.*;

import io.github.cdimascio.dotenv.Dotenv;
import com.sanal.omdb.services.ConsumoApi;
import com.sanal.omdb.services.ConverteDados;

public class Funcoes {

    private Dotenv dotenv = Dotenv.load();
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();

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

    public void listarEpisodios(DadosSerie serie) {
        System.out.println("Listando episódios da temporada da série: " + serie.titulo());

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= serie.temporadas(); i++) {
            String json = retornarDadosEpisodeos(
                serie.titulo(), i
            );

            DadosTemporada dadosTemporada =
                converte.obterDados(json, DadosTemporada.class);

            temporadas.add(dadosTemporada);
        }

        // return temporadas;
        temporadas.forEach(System.out::println);
    }

    public void buscarEpisodioPorNumero(DadosSerie serie, int numero) {
        System.out.println("Buscando episódio número: " + numero);
        // Implementar a lógica para buscar um episódio por número aqui
        try {
            String json = retornarDadosEpisodeos(serie.titulo(), numero);
            DadosEpisodio episodio =
                converte.obterDados(json, DadosEpisodio.class);
            System.out.println(episodio);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o episódio: " + e.getMessage());
        }
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
