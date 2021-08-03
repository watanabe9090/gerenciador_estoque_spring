package com.gerenciador_estoque.dto.setor;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SetorPutRequestBody {
    @NotNull(message = "O ID do Setor não pode ser Nulo")
    private Long id;
    @NotEmpty(message = "O nome alterado não pode ser vazio")
    private String nome;
}