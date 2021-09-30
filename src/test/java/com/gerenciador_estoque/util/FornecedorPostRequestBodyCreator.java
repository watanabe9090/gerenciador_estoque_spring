package com.gerenciador_estoque.util;

import com.gerenciador_estoque.dto.fornecedor.FornecedorPostRequestBody;

public class FornecedorPostRequestBodyCreator {

    public static FornecedorPostRequestBody createFornecedorPostRequestBody() {
        return FornecedorPostRequestBody.builder()
                .cnpj(FornecedorCreator.createFornecedor().getCnpj())
                .nomeFantasia(FornecedorCreator.createFornecedor().getNomeFantasia())
                .razaoSocial(FornecedorCreator.createFornecedor().getRazaoSocial())
                .endereco(FornecedorCreator.createFornecedor().getEndereco())
                .contato(FornecedorCreator.createFornecedor().getContato())
                .build();
    }

}
