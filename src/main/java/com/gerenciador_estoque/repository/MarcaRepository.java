package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByFornecedor(Fornecedor fornecedor);
    List<Marca> findByNomeIgnoreCaseContaining(String nome);
}
