package com.gerenciador_estoque.service;

import com.gerenciador_estoque.dto.colaborador.ColaboradorPostRequestBody;
import com.gerenciador_estoque.dto.colaborador.ColaboradorPutRequestBody;
import com.gerenciador_estoque.exception.BadRequestException;
import com.gerenciador_estoque.model.Colaborador;
import com.gerenciador_estoque.repository.ColaboradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//https://medium.com/sprang/validation-and-exception-handling-with-spring-ba44b3ee0723

@Service
@RequiredArgsConstructor
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;

    public Page<Colaborador> listAll(Pageable pageable) {
        List<Colaborador> colaboradores = colaboradorRepository.findAll(pageable)
                .stream()
                .filter(colaborador -> colaborador.getStatusCode() == 1)
                .collect(Collectors.toList());
        return new PageImpl<>(colaboradores);
    }

    public Colaborador findById(Long id) {
        return colaboradorRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Colaborador não encontrado"));
    }

    public Colaborador save(ColaboradorPostRequestBody colaboradorPostRequestBody) {
        Colaborador colaborador = Colaborador.builder()
                .nome(colaboradorPostRequestBody.getNome())
                .sobrenome(colaboradorPostRequestBody.getSobrenome())
                .sexo(colaboradorPostRequestBody.getSexo())
                .cpf(colaboradorPostRequestBody.getCpf())
                .dataNascimento(colaboradorPostRequestBody.getDataNascimento())
                .contato(colaboradorPostRequestBody.getContato())
                .endereco(colaboradorPostRequestBody.getEndereco())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
        return colaboradorRepository.save(colaborador);
    }

    public void update(ColaboradorPutRequestBody colaboradorPutRequestBody) {
        Colaborador colaborador = findById(colaboradorPutRequestBody.getId());
        colaborador.setNome(colaboradorPutRequestBody.getNome());
        colaborador.setSobrenome(colaboradorPutRequestBody.getSobrenome());
        colaborador.setCpf(colaboradorPutRequestBody.getCpf());
        colaborador.setSexo(colaboradorPutRequestBody.getSexo());
        colaborador.setDataNascimento(colaboradorPutRequestBody.getDataNascimento());
        colaborador.setContato(colaboradorPutRequestBody.getContato());
        colaborador.setEndereco(colaboradorPutRequestBody.getEndereco());
        this.colaboradorRepository.save(colaborador);
    }

    public void delete(Long id) {
        Colaborador colaborador = findById(id);
        colaborador.setStatusCode(0);
        colaboradorRepository.save(colaborador);
    }
}
