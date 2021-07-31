package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    List<Fornecedor> findByNomeFantasiaIgnoreCaseContaining(String nomeFantasia);
    List<Fornecedor> findByCnpjContaining(String cnpj);
    List<Fornecedor> findByRazaoSocialIgnoreCaseContaining(String razaoSocial);
}
