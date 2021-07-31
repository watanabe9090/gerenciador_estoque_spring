package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;

import java.time.LocalDateTime;

public class FornecedorCreator {

    public static Fornecedor createFornecedor() {
        return Fornecedor.builder()
                .id(0L)
                .cnpj("96531451000124")
                .nomeFantasia("Adilene Imoveis")
                .razaoSocial("Adilene da Silva")
                .endereco(Endereco.builder()
                        .cep("01001-000")
                        .bairro("Sé")
                        .complemento("lado ímpar")
                        .localidade("Sao Paulo")
                        .logradouro("Praça da Sé")
                        .numero("888")
                        .build())
                .contato(Contato.builder()
                        .email("adilene@hotmail.com")
                        .telefoneCelular("944445555")
                        .telefoneFixo("44445555")
                        .build())
                .statusCode(1)
                .dataCadastro(LocalDateTime.of(2000, 10, 11, 10, 23, 11))
                .build();
    }


}
