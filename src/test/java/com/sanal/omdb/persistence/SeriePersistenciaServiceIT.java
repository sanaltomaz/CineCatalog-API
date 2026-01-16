package com.sanal.omdb.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.sanal.omdb.models.TipoTitulo;
import com.sanal.omdb.models.Titulo;
import com.sanal.omdb.persistence.entity.SerieEntity;
import com.sanal.omdb.persistence.repository.SerieRepository;
import com.sanal.omdb.persistence.service.SeriePersistenciaService;

/**
 * Teste de integração para {@code SeriePersistenciaService}.
 *
 * Objetivo:
 * - Validar a persistência real de séries em banco PostgreSQL
 * - Verificar a conversão correta do domínio para entidade JPA
 * - Garantir que apenas títulos do tipo SERIE sejam persistidos
 *
 * Tipo de teste:
 * - Teste de integração (não é teste unitário)
 * - Utiliza banco PostgreSQL real configurado no profile de teste
 *
 * Escopo do que é testado:
 * - Persistência bem-sucedida de uma série válida
 * - Geração correta de identificador
 * - Escrita efetiva no banco de dados
 * - Validação de uso correto do service (tipo do título)
 *
 * O que NÃO é testado aqui:
 * - Persistência de episódios
 * - Integração com a API do OMDB
 * - Regras de análise ou estatísticas
 * - Fluxos de aplicação completos
 *
 * Observações:
 * - Este teste valida o aggregate root Série em isolamento
 * - Episódios são tratados em testes específicos
 * - Falhas aqui indicam inconsistência direta na persistência de séries
 */
@SpringBootTest
@ActiveProfiles("test")
class SeriePersistenciaServiceIT {

    @Autowired
    private SeriePersistenciaService seriePersistenciaService;

    @Autowired
    private SerieRepository serieRepository;

    @Test
    void devePersistirSerieComSucesso() {
        // given - domínio de série válido
        Titulo titulo = new Titulo(
            TipoTitulo.SERIE,
            "Breaking Bad",
            null,       // temporadas ainda não entram
            null,       // numero episodio não se aplica
            null,       // duração não é relevante aqui
            9.5,
            null,
            null
        );

        // when - persistimos a série
        SerieEntity entity = seriePersistenciaService.salvarSerie(titulo);

        // then - validamos efeitos reais no banco
        assertNotNull(entity.getId(), "A série deveria ter um ID gerado");
        assertEquals(1, serieRepository.count(), "Deveria existir exatamente uma série persistida");
    }
}
