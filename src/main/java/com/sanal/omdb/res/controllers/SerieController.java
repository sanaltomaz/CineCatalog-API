package com.sanal.omdb.res.controllers;

import com.sanal.omdb.application.injestao.SerieIngestaoApplicationService;
import com.sanal.omdb.res.dto.IngestaoRequestDto;
import com.sanal.omdb.res.dto.SerieResponseDto;
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
    public ResponseEntity<String> ingerirSerie
    (
        @RequestBody IngestaoRequestDto request
    ) {
        ingestaoService.ingestarSerie(request.nome());

        return ResponseEntity.status(
            HttpStatus.CREATED
        ).body("Série ingerida com sucesso!");
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SerieResponseDto> buscarPorId(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(queryService.buscarSeriePorId(id));
    }

    @GetMapping
    public ResponseEntity<List<SerieResponseDto>> listar() {
        return ResponseEntity.ok(queryService.listarTodasSeries());
    }
}
