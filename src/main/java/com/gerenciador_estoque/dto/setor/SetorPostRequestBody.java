package com.gerenciador_estoque.dto.setor;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SetorPostRequestBody {
    @NotEmpty(message = "O nome do setor não pode ser vazio")
    private String nome;
    @NotNull(message = "O ID do local não pode ser Nulo")
    private Long localId;
}
