package com.gerenciador_estoque.dto.colaborador;

import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ColaboradorPostRequestBody {
    private String nome;
    private String sobrenome;
    private String sexo;
    private String cpf;
    private LocalDate dataNascimento;
    private Contato contato;
    private Endereco endereco;
}
