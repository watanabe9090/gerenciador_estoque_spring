package com.gerenciador_estoque.dto.marca;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class MarcaPostRequestBody {
    private String nome;
    private Long fornecedorId;
}
