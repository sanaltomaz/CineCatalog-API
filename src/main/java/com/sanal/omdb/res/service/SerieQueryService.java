package com.sanal.omdb.res.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sanal.omdb.persistence.entity.SerieEntity;
import com.sanal.omdb.persistence.repository.SerieRepository;
import com.sanal.omdb.res.dto.SerieResponseDto;

@Service
public class SerieQueryService {
    
    private final SerieRepository serieRepository;

    public SerieQueryService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    private SerieResponseDto toDto(SerieEntity entity) {
        return new SerieResponseDto(
            entity.getId(),
            entity.getTitulo(),
            entity.getAvaliacao(),
            entity.getSinopse(),
            entity.getTotalTemporadas()
        );
    }

    public SerieResponseDto buscarSeriePorTitulo(String titulo) {
        SerieEntity serieEntity = serieRepository.findByTitulo(titulo)
            .orElseThrow(() -> new RuntimeException("Série não encontrada: " + titulo));
        
        return toDto(serieEntity);
    }

    public SerieResponseDto buscarSeriePorId(Long id) {
        SerieEntity serieEntity = serieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Série não encontrada: ID " + id));
        
        return toDto(serieEntity);
    }

    public List<SerieResponseDto> listarTodasSeries() {
        return serieRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}
