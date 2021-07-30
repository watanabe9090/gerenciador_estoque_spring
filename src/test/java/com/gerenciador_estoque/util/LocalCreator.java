package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.model.util.Endereco;

import java.time.LocalDateTime;

public class LocalCreator {
    public static Local createLocal() {
        Local local = Local.builder()
                .nome("Local Alfa")
                .endereco(Endereco.builder()
                        .cep("76961749")
                        .logradouro("Rua Catarino Cardoso")
                        .localidade("Cacoal")
                        .bairro("Conjunto Halley")
                        .complemento("")
                        .numero("167")
                        .build())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
        return local;
    }
}
