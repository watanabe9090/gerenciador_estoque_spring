package com.gerenciador_estoque.model.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data @Entity @Builder
@NoArgsConstructor @AllArgsConstructor
public class Contato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @Email
    private String email;
    @NotNull @Pattern(regexp = "[\\d]+")
    private String telefoneCelular;
    @NotNull @Pattern(regexp = "[\\d]+")
    private String telefoneFixo;
}
