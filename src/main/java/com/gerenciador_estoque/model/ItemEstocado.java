package com.gerenciador_estoque.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ItemEstocado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private double precoCompra;
    private double precoVenda;

    @OneToOne
    @JoinColumn(name = "lote_id")
    private Lote lote;

    @OneToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

    @ManyToOne
    @JoinColumn(name = "mercadoria_id")
    private Mercadoria mercadoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Value(value = "{1}")
    private int statusCode;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
}
