package com.gerenciador_estoque.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Pattern(regexp = "[a-zA-Z]+")
    private String nome;
    @NotNull @Pattern(regexp = "[\\sa-zA-Z]+")
    private String sobrenome;
    @NotNull @Pattern(regexp = "[mfoMFO]{1}")
    private String sexo;
    @NotNull @Pattern(regexp = "[\\d]{11}")
    private String cpf;
    @NotNull @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id")
    private Contato contato;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    private int statusCode;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
}
