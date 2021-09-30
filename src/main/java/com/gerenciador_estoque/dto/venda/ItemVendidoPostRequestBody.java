package com.gerenciador_estoque.dto.venda;

import lombok.Data;

@Data
public class ItemVendidoPostRequestBody {
    private Long itemEstocadoId;
    private Integer quantidade;
}
