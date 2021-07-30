package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findByNomeIgnoreCaseContaining(String nome);
}
