package com.gerenciador_estoque.dto.fornecedor;

import com.gerenciador_estoque.model.Marca;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class FornecedorPostRequestBody {
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private Endereco endereco;
    private Contato contato;
}
