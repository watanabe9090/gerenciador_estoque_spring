package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;

import java.time.LocalDateTime;

public class FornecedorPutRequestBodyCreator {

    public static Fornecedor createFornecedor() {
        return Fornecedor.builder()
                .id(0L)
                .cnpj(FornecedorCreator.createFornecedor().getCnpj())
                .nomeFantasia(FornecedorCreator.createFornecedor().getNomeFantasia())
                .razaoSocial(FornecedorCreator.createFornecedor().getRazaoSocial())
                .endereco(FornecedorCreator.createFornecedor().getEndereco())
                .contato(FornecedorCreator.createFornecedor().getContato())
                .build();
    }

}
