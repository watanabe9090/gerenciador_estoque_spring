package com.gerenciador_estoque.dto.fornecedor;

import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class FornecedorPutRequestBody {
    private Long id;
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private Endereco endereco;
    private Contato contato;
}
