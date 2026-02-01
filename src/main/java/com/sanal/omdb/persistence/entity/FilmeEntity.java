package com.sanal.omdb.persistence.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidade JPA que representa um filme persistido no sistema.
 *
 * <p>
 * Responsabilidade:
 * - Armazenar apenas os metadados básicos do filme
 *
 * <p>
 * Observações:
 * - Não contém regras de negócio
 * - Não contém relacionamentos
 */
@Entity
public class FilmeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracao;
    private Double avaliacao;
    private String sinopse;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    public FilmeEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
