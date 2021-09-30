package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.ItemVendido;
import com.gerenciador_estoque.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVendidoRepository extends JpaRepository<ItemVendido, Long> {
    List<ItemVendido> findByVenda(Venda venda);
}
