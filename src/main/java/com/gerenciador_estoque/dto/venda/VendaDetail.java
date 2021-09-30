package com.gerenciador_estoque.dto.venda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gerenciador_estoque.model.ItemVendido;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class VendaDetail {
    private Long vendaId;
    private Double valor;
    private LocalDateTime dataCadastro;
    private List<ItemVendido> itensVendidos;
}
