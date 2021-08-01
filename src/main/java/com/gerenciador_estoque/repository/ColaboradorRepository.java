package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    List<Colaborador> findByNomeIgnoreCaseContaining(String nome);
    List<Colaborador> findBySobrenomeIgnoreCaseContaining(String sobrenome);
    List<Colaborador> findByCpfContaining(String cpf);
    List<Colaborador> findBySexoIgnoreCase(String sexo);
    List<Colaborador> findByDataNascimentoBetween(LocalDate start, LocalDate end);
}
