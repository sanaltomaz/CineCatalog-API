package com.sanal.omdb.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidade JPA que representa uma série persistida no sistema.
 * 
 * <p>
 * Responsabilidade:
 * - Armazenar apenas os metadados básicos da série
 *
 * <p>
 * Observações:
 * - Não contém episódios
 * - Não contém regras de negócio
 */
@Entity
public class    SerieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;
    private String avaliacao;
    private String sinopse;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "total_temporadas", nullable = false)
    private Integer totalTemporadas;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public SerieEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }
}
