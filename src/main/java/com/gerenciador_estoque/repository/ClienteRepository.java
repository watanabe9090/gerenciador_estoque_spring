package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeIgnoreCaseContaining(String nome);
    List<Cliente> findBySobrenomeIgnoreCaseContaining(String sobrenome);
    List<Cliente> findByCpfContaining(String cpf);
    List<Cliente> findBySexoIgnoreCase(String sexo);
}
