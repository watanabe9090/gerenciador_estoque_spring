package com.gerenciador_estoque.dto.local;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciador_estoque.model.Setor;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LocalPostRequestBody {
    private String nome;
    private Endereco endereco;
}
