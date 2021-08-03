package com.gerenciador_estoque.service;

import com.gerenciador_estoque.dto.setor.SetorPostRequestBody;
import com.gerenciador_estoque.dto.setor.SetorPutRequestBody;
import com.gerenciador_estoque.model.Cliente;
import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.model.Setor;
import com.gerenciador_estoque.repository.LocalRepository;
import com.gerenciador_estoque.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SetorService {
    private final SetorRepository setorRepository;
    private final LocalRepository localRepository;

    private Page<Setor> filter(Page<Setor> setores) {
        List<Setor> setorList = setores
                .stream()
                .filter(setor -> setor.getStatusCode() == 1)
                .collect(Collectors.toList());
        return new PageImpl<>(setorList);
    }

    private List<Setor> filter(List<Setor> setores) {
        return setores
                .stream()
                .filter(setor -> setor.getStatusCode() == 1)
                .collect(Collectors.toList());
    }


    public Page<Setor> listAll(Pageable pageable) {
        return filter(setorRepository.findAll(pageable));
    }

    public Setor findById(Long id) {
        Setor setor = setorRepository.findById(id).get();
        return setor;
    }

    public List<Setor> findByLocalId(Long id) {
        Local local = localRepository.findById(id).get();
        List<Setor> setores = setorRepository.findByLocal(local);
        return filter(setores);
    }

    public Setor save(SetorPostRequestBody setorPostRequestBody) {
        System.out.println(setorPostRequestBody.toString());
        Local local = localRepository.findById(setorPostRequestBody.getLocalId()).get();
        Setor setor = Setor.builder()
                .nome(setorPostRequestBody.getNome())
                .local(local)
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
        return setorRepository.save(setor);
    }

    public void update(SetorPutRequestBody setorPutRequestBody) {
        Setor setor = findById(setorPutRequestBody.getId());
        setor.setNome(setorPutRequestBody.getNome());
        setorRepository.save(setor);
    }

    public void delete(Long id) {
        Setor setor = findById(id);
        setor.setStatusCode(0);
        setorRepository.save(setor);
    }
}
