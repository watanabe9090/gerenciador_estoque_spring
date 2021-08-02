package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.util.Contato;

public class ContatoCreator {
    public static Contato createContato() {
        return Contato.builder()
                .email("random92@hotmail.com")
                .telefoneFixo("43439292")
                .telefoneCelular("951203333")
                .build();
    }
}
