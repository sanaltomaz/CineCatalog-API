package com.sanal.omdb.principal;

import java.util.Scanner;
import com.sanal.omdb.services.IdentificarTipo;
import com.sanal.omdb.services.ConverteDados;

public class Principal {
    
    private Scanner scanner = new Scanner(System.in);
    private Funcoes Funcoes = new Funcoes();
    
    private IdentificarTipo identificador = new IdentificarTipo();
    

    

    public void iniciarMenu() {
        System.out.println("Digite o nome de um Titulo: ");
        String nomeDoFilme = scanner.nextLine();

        var json = Funcoes.retornarDadosTitulo(nomeDoFilme);

        Class<?> tipoClass = identificador.identificarTipo(json);
        Object dados = new ConverteDados().obterDados(json, tipoClass);
        System.out.println(dados);

        // if (classe == DadosFilme.class) {
        //     DadosFilme dados = converte.obterDados(json, DadosFilme.class);
        //     Titulo titulo = new Titulo(dados);
        //     System.out.println(titulo);
        // } else if (classe == DadosSerie.class) {
        //     DadosSerie dados = converte.obterDados(json, DadosSerie.class);
        //     Titulo titulo = new Titulo(dados);
        //     System.out.println(titulo);
        // } else if (classe == DadosEpisodio.class) {
        //     DadosEpisodio dados = converte.obterDados(json, DadosEpisodio.class);
        //     Titulo titulo = new Titulo(dados);
        //     System.out.println(titulo);
        // }

    }
}
