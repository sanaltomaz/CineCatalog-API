package com.sanal.omdb.principal;

import java.util.ArrayList;
import java.util.List;

import com.sanal.omdb.models.*;

import com.sanal.omdb.services.ConverteDados;
import com.sanal.omdb.services.RetornoDados;

public class Funcoes {
    private ConverteDados converte = new ConverteDados();
    private RetornoDados retorno = new RetornoDados();

    public void listarEpisodios(DadosSerie serie) {
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

        // return temporadas;
        temporadas.forEach(System.out::println);
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
