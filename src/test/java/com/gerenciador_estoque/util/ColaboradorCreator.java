package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Colaborador;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ColaboradorCreator {
    public static Colaborador createColaborador() {
        return Colaborador.builder()
                .nome("Edward")
                .sobrenome("Elric")
                .sexo("m")
                .cpf("57404912075")
                .dataNascimento(LocalDate.of(2000, 10, 10))
                .contato(Contato.builder()
                        .build())
                .endereco(Endereco.builder()
                        .build())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
    }
}
