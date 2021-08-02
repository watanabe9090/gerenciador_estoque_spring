package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;

import java.time.LocalDateTime;

public class FornecedorCreator {

    public static Fornecedor createFornecedor() {
        return Fornecedor.builder()
                .cnpj("96531451000124")
                .nomeFantasia("Adilene Imoveis")
                .razaoSocial("Adilene da Silva")
                .endereco(EnderecoCreator.createEndereco())
                .contato(ContatoCreator.createContato())
                .statusCode(1)
                .dataCadastro(LocalDateTime.of(2000, 10, 11, 10, 23, 11))
                .build();
    }


}
