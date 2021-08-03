package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long> {
}
