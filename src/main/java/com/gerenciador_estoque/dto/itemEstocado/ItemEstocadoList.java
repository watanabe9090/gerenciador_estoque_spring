package com.gerenciador_estoque.dto.itemEstocado;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ItemEstocadoList {
    private Long id;
    private String codigo;
    private String nome;
    private Integer quantidade;
    private Double precoVenda;
}
