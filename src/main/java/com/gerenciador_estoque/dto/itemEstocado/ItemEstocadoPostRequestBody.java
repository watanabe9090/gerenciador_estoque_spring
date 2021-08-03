package com.gerenciador_estoque.dto.itemEstocado;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciador_estoque.model.Marca;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ItemEstocadoPostRequestBody {
    private String nome;
    private String codigo;
    private String unidade;
    private Double precoCompra;
    private Double precoVenda;
    private String descricao;
    private String descricaoReduzida;

    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;

    private Long marcaId;
    private Long setorId;
}
