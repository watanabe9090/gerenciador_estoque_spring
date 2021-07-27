package com.gerenciador_estoque.util;

import com.gerenciador_estoque.model.Lote;

import java.time.LocalDate;

public class LoteCreator {
    public static Lote createLote() {
        return Lote.builder()
                .dataFabricacao(LocalDate.of(2019, 11, 10))
                .dataVencimento(LocalDate.of(2022, 10, 9))
                .build();
    }
}
