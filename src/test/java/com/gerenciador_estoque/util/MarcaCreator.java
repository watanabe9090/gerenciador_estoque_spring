package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Marca;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MarcaCreator {
    public static Marca createMarca() {
        return Marca.builder()
                .nome("Marca Alpha Beta")
                .fornecedor(FornecedorCreator.createFornecedor())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
    }

}
