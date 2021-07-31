package com.gerenciador_estoque.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Marca {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nome;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "forncedor_id", nullable = false)
    private Fornecedor fornecedor;
    private int statusCode;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
}
