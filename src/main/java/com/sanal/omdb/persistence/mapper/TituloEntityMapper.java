package com.sanal.omdb.persistence.mapper;

import com.sanal.omdb.models.TipoTitulo;
import com.sanal.omdb.models.Titulo;
import com.sanal.omdb.persistence.entity.FilmeEntity;

public class TituloEntityMapper {

    public FilmeEntity toFilmeEntity(Titulo titulo) {
        if (titulo == null) {
            return null;
        }

        if (titulo.getTipo() != TipoTitulo.FILME) {
            throw new IllegalArgumentException("Título não é um filme");
        }

        FilmeEntity entity = new FilmeEntity();
        entity.setTitulo(titulo.getTitulo());
        entity.setAvaliacao(titulo.getAvaliacao());
        entity.setDataLancamento(titulo.getDataLancamento());
        entity.setSinopse(titulo.getSinopse());
        entity.setTipo(titulo.getTipo());
        entity.setDuracao(titulo.getDuracao());

        return entity;
    }
}
