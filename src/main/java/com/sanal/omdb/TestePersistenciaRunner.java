package com.sanal.omdb;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sanal.omdb.models.TipoTitulo;
import com.sanal.omdb.models.Titulo;
import com.sanal.omdb.persistence.service.SeriePersistenciaService;
import com.sanal.omdb.services.TituloService;

/**
 * Runner temporário para validar a persistência de séries.
 *
 * Observações:
 * - Classe exclusivamente para teste manual de infraestrutura
 * - Deve ser removida após validação da persistência
 * - NÃO representa fluxo real da aplicação
 */
@Configuration
public class TestePersistenciaRunner {

    @Bean
    ApplicationRunner testarPersistenciaSerie(
        TituloService tituloService,
        SeriePersistenciaService seriePersistenciaService
    ) {
        return args -> {

            System.out.println("=== TESTE DE PERSISTÊNCIA DE SÉRIE ===");

            Titulo titulo = tituloService.buscarPorNome("Friends");

            if (titulo == null) {
                throw new IllegalStateException("Título não encontrado na OMDB");
            }

            if (titulo.getTipo() != TipoTitulo.SERIE) {
                throw new IllegalStateException(
                    "Título retornado não é uma série: " + titulo.getTipo()
                );
            }

            seriePersistenciaService.salvarSerie(titulo);

            System.out.println("Série persistida com sucesso:");
            System.out.println(" - Título: " + titulo.getTitulo());
            System.out.println(" - Temporadas: " + titulo.getTemporadas());
            System.out.println(" - Avaliação: " + titulo.getAvaliacao());
        };
    }
}
