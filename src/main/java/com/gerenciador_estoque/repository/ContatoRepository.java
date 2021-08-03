package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.util.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByEmailIgnoreCaseContaining(String email);
    List<Contato> findByTelefoneCelularIgnoreCaseContaining(String telefoneCelular);
    List<Contato> findByTelefoneFixoIgnoreCaseContaining(String telefoneFixo);
}
