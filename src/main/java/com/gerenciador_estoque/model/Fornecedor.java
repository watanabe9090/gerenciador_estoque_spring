package com.gerenciador_estoque.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.model.util.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Fornecedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nomeFantasia;
    @NotEmpty @Pattern(regexp = "[\\sa-zA-Z]+")
    private String razaoSocial;
    @NotNull @Pattern(regexp = "[\\d]{14}")
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id")
    private Contato contato;
    private int statusCode;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
}
