package com.gerenciador_estoque.service;

import com.gerenciador_estoque.dto.local.LocalPostRequestBody;
import com.gerenciador_estoque.dto.local.LocalPutRequestBody;
import com.gerenciador_estoque.exception.BadRequestException;
import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalService {
    private final LocalRepository localRepository;

    public Page<Local> listAll(Pageable pageable) {
        return localRepository.findAll(pageable);
    }

    public Local findById(Long id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Local não encontrado"));
    }

    public Local save(LocalPostRequestBody localPostRequestBody) {
        Local local = Local.builder()
                .nome(localPostRequestBody.getNome())
                .endereco(localPostRequestBody.getEndereco())
                .build();
        return localRepository.save(local);
    }

    public void update(LocalPutRequestBody localPutRequestBody) {
        Local local = findById(localPutRequestBody.getId());
        local.setNome(localPutRequestBody.getNome());
        local.setEndereco(localPutRequestBody.getEndereco());
        localRepository.save(local);
    }

    public void delete(Long id) {
        Local local = findById(id);
        local.setStatusCode(0);
        localRepository.save(local);
    }

}
