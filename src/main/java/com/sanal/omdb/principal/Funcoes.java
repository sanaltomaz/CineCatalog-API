package com.sanal.omdb.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.sanal.omdb.models.*;

import com.sanal.omdb.services.ConverteDados;
import com.sanal.omdb.services.RetornoDados;

public class Funcoes {
    private ConverteDados converte = new ConverteDados();
    private RetornoDados retorno = new RetornoDados();

    public List<DadosTemporada> listarEpisodios(DadosSerie serie) {
        System.out.println("Listando episódios da temporada da série: " + serie.titulo());

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= serie.temporadas(); i++) {
            String json = retorno.retornarDadosEpisodeos(
                serie.titulo(), i
            );

            DadosTemporada dadosTemporada =
                converte.obterDados(json, DadosTemporada.class);

            temporadas.add(dadosTemporada);
        }

        return temporadas;
        // temporadas.forEach(System.out::println);
    }

    public void listarMelhoresEpisodios(DadosSerie serie) {
        var temporadas = listarEpisodios(serie);
        List<DadosEpisodio> dadosEpisodio = temporadas.stream()
            .flatMap(t -> t.episodios().stream())
            .collect(Collectors.toList());

        System.out.println("Listando melhores episódios...");
        // Implementar a lógica para listar os melhores episódios aqui
        dadosEpisodio.stream()
            .filter(e -> !e.avaliacao().equals("N/A"))
            .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
            .limit(5)
            .forEach(System.out::println);
    }

    public void listarPioresEpisodios() {
        System.out.println("Listando piores episódios...");
        // Implementar a lógica para listar os piores episódios aqui
    }
}
