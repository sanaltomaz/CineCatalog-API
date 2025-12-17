package com.sanal.omdb.services;

import com.sanal.omdb.models.*;

// import com.sanal.omdb.models.*;

public class IdentificarClasse {
    
    public Class<?> identificarTipo (String json) {
        if (json.contains("\"Type\":\"movie\"")) {
            return DadosFilme.class;
        } else if (json.contains("\"Type\":\"series\"")) {
            return DadosSerie.class;
        } else if (json.contains("\"Episode\":")) {
            return DadosEpisodio.class;
        }
        throw new IllegalArgumentException("Tipo desconhecido no JSON");
    }
}
