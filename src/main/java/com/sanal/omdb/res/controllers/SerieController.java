package com.sanal.omdb.res.controllers;

import com.sanal.omdb.application.injestao.SerieIngestaoApplicationService;
import com.sanal.omdb.application.injestao.result.SerieIngestaoResultado;
import com.sanal.omdb.res.dto.SerieIngestaoRequestDto;
import com.sanal.omdb.res.dto.SerieIngestaoResponseDto;
import com.sanal.omdb.res.service.SerieQueryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieIngestaoApplicationService ingestaoService;
    private final SerieQueryService queryService;

    public SerieController(SerieIngestaoApplicationService ingestaoService, SerieQueryService queryService) {
        this.ingestaoService = ingestaoService;
        this.queryService = queryService;
    }

    @PostMapping("/ingest")
    public ResponseEntity<SerieIngestaoResponseDto> ingerirSerie(
        @RequestBody SerieIngestaoRequestDto request
    ) {
        SerieIngestaoResultado resultado =
                ingestaoService.ingestarSerie(request.nome());

        SerieIngestaoResponseDto response =
                new SerieIngestaoResponseDto(
                        resultado.titulo(),
                        resultado.totalTemporadas(),
                        resultado.totalEpisodios(),
                        resultado.criada()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieIngestaoResponseDto> buscarPorId(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(queryService.buscarSeriePorId(id));
    }

    @GetMapping
    public ResponseEntity<List<SerieIngestaoResponseDto>> listar() {
        return ResponseEntity.ok(queryService.listarTodasSeries());
    }

    @GetMapping("/titulo")
    public ResponseEntity<SerieIngestaoResponseDto> buscarPorTitulo(
        @RequestParam String titulo
    ) {
        return ResponseEntity.ok(queryService.buscarSeriePorTitulo(titulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarSerie(
        @PathVariable Long id
    ) {
        queryService.deletarSerie(id);
        return ResponseEntity.ok("Série deletada com sucesso!");
    }
}
