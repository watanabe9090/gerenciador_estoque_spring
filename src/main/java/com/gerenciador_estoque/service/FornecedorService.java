package com.gerenciador_estoque.service;

import com.gerenciador_estoque.dto.fornecedor.FornecedorPostRequestBody;
import com.gerenciador_estoque.dto.fornecedor.FornecedorPutRequestBody;
import com.gerenciador_estoque.exception.BadRequestException;
import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    private List<Fornecedor> filter(List<Fornecedor> fornecedores) {
        return fornecedores.stream()
                .filter(fornecedor -> fornecedor.getStatusCode() == 1)
                .collect(Collectors.toList());
    }

    public Page<Fornecedor> listAll(Pageable pageable) {
        return new PageImpl<>(filter(fornecedorRepository.findAll()));
    }

    public Fornecedor findById(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Fornecedor Not Found"));
    }

    public List<Fornecedor> findByNomeFantasia(String nomeFantasia) {
        return fornecedorRepository.findByNomeFantasiaIgnoreCaseContaining(nomeFantasia);
    }
    // New Methods
    public Page<Fornecedor> findByNomeFantasia(String nomeFantasia, Pageable pageable) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByNomeFantasiaIgnoreCaseContaining(nomeFantasia);
        return new PageImpl<>(filter(fornecedores), pageable, pageable.getPageSize());
    }

    public Page<Fornecedor> findByCnpj(String cnpj, Pageable pageable) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByCnpjContaining(cnpj);
        return new PageImpl<>(filter(fornecedores), pageable, pageable.getPageSize());
    }

    public Page<Fornecedor> findByRazaoSocial(String razaoSocial, Pageable pageable) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByRazaoSocialIgnoreCaseContaining(razaoSocial);
        return new PageImpl<>(filter(fornecedores), pageable, pageable.getPageSize());
    }

    public Fornecedor save(FornecedorPostRequestBody fornecedorPostRequestBody) {
        Fornecedor fornecedor = Fornecedor.builder()
                .cnpj(fornecedorPostRequestBody.getCnpj())
                .nomeFantasia(fornecedorPostRequestBody.getNomeFantasia())
                .razaoSocial(fornecedorPostRequestBody.getRazaoSocial())
                .endereco(fornecedorPostRequestBody.getEndereco())
                .contato(fornecedorPostRequestBody.getContato())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
        return fornecedorRepository.save(fornecedor);
    }

    public void update(FornecedorPutRequestBody fornecedorPutRequestBody) {
        Fornecedor fornecedor = findById(fornecedorPutRequestBody.getId());
        fornecedor.setRazaoSocial(fornecedorPutRequestBody.getRazaoSocial());
        fornecedor.setNomeFantasia(fornecedorPutRequestBody.getNomeFantasia());
        fornecedor.setCnpj(fornecedorPutRequestBody.getCnpj());
        fornecedor.setContato(fornecedorPutRequestBody.getContato());
        fornecedor.setEndereco(fornecedorPutRequestBody.getEndereco());
        fornecedorRepository.save(fornecedor);
    }

    public void delete(Long id) {
        Fornecedor fornecedor = findById(id);
        fornecedor.setStatusCode(0);
        fornecedorRepository.save(fornecedor);
    }
}
