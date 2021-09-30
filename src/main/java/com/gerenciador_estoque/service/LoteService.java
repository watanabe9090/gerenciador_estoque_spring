package com.gerenciador_estoque.service;

import com.gerenciador_estoque.model.Lote;
import com.gerenciador_estoque.repository.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteService {
    private final LoteRepository loteRepository;

    public List<Lote> listAll() {
        return loteRepository.findAll();
    }

    public Lote findById(Long id) {
        return loteRepository.findById(id).get();
    }

    public Lote save(Lote lote) {
        //if(lote.getDataFabricacao().isAfter(lote.getDataVencimento());
        return null;
    }
}
