package com.gerenciador_estoque.dto.cliente;

import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.Data;

@Data
public class ClientePostRequestBody {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String sexo;
    private Endereco endereco;
    private Contato contato;
}
