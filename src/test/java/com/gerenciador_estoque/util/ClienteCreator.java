package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Cliente;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;

import java.time.LocalDateTime;

public class ClienteCreator {
    public static Cliente createCliente() {
        return Cliente.builder()
                .nome("Jose")
                .sobrenome("Ferreira")
                .sexo("m")
                .cpf("57404912075")
                .contato(Contato.builder()
                        .build())
                .endereco(Endereco.builder()
                        .build())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
    }
}
