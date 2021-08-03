package com.gerenciador_estoque.dto.cliente;

import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.Data;

@Data
public class ClientePutRequestBody {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String sexo;
    private Contato contato;
    private Endereco endereco;
}
