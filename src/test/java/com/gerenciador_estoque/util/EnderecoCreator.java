package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.util.Endereco;

public class EnderecoCreator {
    public static Endereco createEndereco() {
        return Endereco.builder()
                .cep("01001-000")
                .bairro("Sé")
                .complemento("lado ímpar")
                .localidade("Sao Paulo")
                .logradouro("Praça da Sé")
                .numero("888")
                .build();
    }
}
