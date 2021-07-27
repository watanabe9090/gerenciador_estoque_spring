package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote> findByDataVencimentoBetween(LocalDate start, LocalDate end);
}
