package com.gerenciador_estoque.dto.local;

import com.gerenciador_estoque.model.util.Endereco;
import lombok.Data;

@Data
public class LocalPutRequestBody {
    private Long id;
    private String nome;
    private Endereco endereco;
}
