package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.ItemEstocado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemEstocadoRepository extends JpaRepository<ItemEstocado, Long> {

}
