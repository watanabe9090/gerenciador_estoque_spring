package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.model.util.Endereco;

import java.time.LocalDateTime;

public class LocalCreator {
    public static Local createLocal() {
        Local local = Local.builder()
                .nome("Local Alfa")
                .endereco(EnderecoCreator.createEndereco())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
        return local;
    }
}
