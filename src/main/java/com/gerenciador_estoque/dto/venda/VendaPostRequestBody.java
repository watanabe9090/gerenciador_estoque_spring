package com.gerenciador_estoque.dto.venda;

import com.gerenciador_estoque.model.ItemEstocado;
import com.gerenciador_estoque.model.Venda;
import lombok.Data;

import java.util.List;

@Data
public class VendaPostRequestBody {
    private List<ItemVendidoPostRequestBody> itensVendidos;
    private Double valor;
}
