package com.gerenciador_estoque.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador_estoque.dto.marca.MarcaPostRequestBody;
import com.gerenciador_estoque.dto.marca.MarcaPutRequestBody;
import com.gerenciador_estoque.exception.BadRequestException;
import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.model.Marca;
import com.gerenciador_estoque.repository.FornecedorRepository;
import com.gerenciador_estoque.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaService {
    private final MarcaRepository marcaRepository;
    private final FornecedorService fornecedorService;

    private Page<Marca> filter(Page<Marca> marcas) {
        List<Marca> marcaList = marcas
                .stream()
                .filter(marca -> marca.getStatusCode() == 1)
                .collect(Collectors.toList());
        return new PageImpl<>(marcaList);
    }

    private List<Marca> filter(List<Marca> marcas) {
        return marcas
                .stream()
                .filter(marca -> marca.getStatusCode() == 1)
                .collect(Collectors.toList());
    }

    public Page<Marca> listAll(Pageable pageable) {
        return filter(marcaRepository.findAll(pageable));
    }

    public Marca findById(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Marca não encontrada"));
        return marca;
    }

    public List<Marca> findByFornecedor(Long fornecedorId) {
        Fornecedor fornecedor = fornecedorService.findById(fornecedorId);
        List<Marca> marcas = marcaRepository.findByFornecedor(fornecedor);
        return filter(marcas);
    }

    public Marca save(MarcaPostRequestBody marcaPostRequestBody) {
        Fornecedor fornecedor = fornecedorService.findById(marcaPostRequestBody.getFornecedorId());
        Marca marca = Marca.builder()
                .nome(marcaPostRequestBody.getNome())
                .fornecedor(fornecedor)
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
        return marcaRepository.save(marca);
    }

    public void update(MarcaPutRequestBody marcaPutRequestBody) {
        Marca marca = findById(marcaPutRequestBody.getId());
        marca.setNome(marcaPutRequestBody.getNome());
        marcaRepository.save(marca);
    }

    public void delete(Long id) {
        Marca marca = findById(id);
        marca.setStatusCode(0);
        marcaRepository.save(marca);
    }
}
