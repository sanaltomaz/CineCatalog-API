package com.sanal.omdb.principal;

import java.util.Scanner;

import com.sanal.omdb.models.*;

public class Menus {
    Scanner scanner = new Scanner(System.in);

    public void iniciarMenus() {
        System.out.println("Bem-vindo ao sistema OMDB!");
        System.out.println("1. Buscar Título");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void funcoesMenu(Class<?> tipoClass) {
        if (tipoClass == DadosSerie.class) {
            System.out.println("Funções disponíveis para séries:");
            System.out.println("1. Listar episódios");
            System.out.println("2. Buscar episódio por número");
            System.out.println("Em criação...");
            System.out.println("3. Buscar episódios por temporada");
            System.out.println("4. Exibir melhores episódios");
            System.out.println("5. Exibir piores episódios");
            System.out.println("6. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
        }
    }
}
