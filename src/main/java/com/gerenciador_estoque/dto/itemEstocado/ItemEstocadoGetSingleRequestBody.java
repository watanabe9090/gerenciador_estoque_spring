package com.gerenciador_estoque.dto.itemEstocado;

import com.gerenciador_estoque.model.Marca;
import com.gerenciador_estoque.model.Setor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data @Builder
public class ItemEstocadoGetSingleRequestBody {
    private String codigo;
    private String nome;
    private String unidade;
    private Double precoCompra;
    private Double precoVenda;
    private String descricao;
    private String descricaoReduzida;
    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;
    private String marcaNome;
    private String fornecedorNomeFantasia;
    private String localNome;
    private String setorNome;
}
