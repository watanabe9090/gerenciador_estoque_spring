package com.gerenciador_estoque.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Mercadoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @Size(min = 2, max = 127)
    private String nome;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "marca_id")
    private Marca marca;
    private String unidade;
    @NotEmpty
    private String descricao;
    @NotEmpty
    private String descricaoReduzida;
    @Value("1")
    private int statusCode;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
}
