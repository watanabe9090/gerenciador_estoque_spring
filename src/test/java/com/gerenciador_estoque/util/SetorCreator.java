package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.model.Setor;

public class SetorCreator {
    public static Setor createSetor() {
        return Setor.builder()
                .nome("Setor Omega")
                .local(LocalCreator.createLocal())
                .build();
    }

}
