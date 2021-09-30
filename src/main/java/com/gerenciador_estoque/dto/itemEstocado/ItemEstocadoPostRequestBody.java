package com.gerenciador_estoque.dto.itemEstocado;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemEstocadoPostRequestBody {
    // Mercadoria
    private String nome;
    private String codigo;
    private String unidade;
    private String descricao;
    private String descricaoReduzida;

    // Item Estocado
    private Double precoCompra;
    private Double precoVenda;
    private Integer quantidade;

    //Lote
    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;

    private Long marcaId;
    private Long setorId;
}
