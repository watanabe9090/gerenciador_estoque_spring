package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    List<Setor> findByLocal(Local local);
    List<Setor> findByNomeIgnoreCaseContaining(String nome);
}
