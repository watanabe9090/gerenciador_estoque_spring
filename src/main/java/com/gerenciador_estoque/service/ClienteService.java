package com.gerenciador_estoque.service;

import com.gerenciador_estoque.dto.cliente.ClientePostRequestBody;
import com.gerenciador_estoque.dto.cliente.ClientePutRequestBody;
import com.gerenciador_estoque.exception.BadRequestException;
import com.gerenciador_estoque.model.Cliente;
import com.gerenciador_estoque.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    private List<Cliente> filter(Page<Cliente> clientes) {
        return clientes
                .stream()
                .filter(cliente -> cliente.getStatusCode() == 1)
                .collect(Collectors.toList());
    }
    private List<Cliente> filter(List<Cliente> clientes) {
        return clientes
                .stream()
                .filter(cliente -> cliente.getStatusCode() == 1)
                .collect(Collectors.toList());
    }
    public Page<Cliente> listAll(Pageable pageable) {
        Page<Cliente> clientePage = clienteRepository.findAll(pageable);
        return new PageImpl<>(filter(clientePage), pageable , pageable.getPageSize());
    }
    public Cliente findById(long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Cliente não encontrado"));
    }
    public Page<Cliente> findByNome(String nome) {
        List<Cliente> clientes = filter(this.clienteRepository.findByNomeIgnoreCaseContaining(nome));
        return new PageImpl<>(clientes);
    }
    public Page<Cliente> findBySobrenome(String sobrenome) {
        List<Cliente> clientes = filter(this.clienteRepository.findBySobrenomeIgnoreCaseContaining(sobrenome));
        return new PageImpl<>(clientes);
    }
    public Page<Cliente> findByCpf(String cpf) {
        List<Cliente> clientes = filter(this.clienteRepository.findByCpfContaining(cpf));
        return new PageImpl<>(clientes);
    }
    public Cliente save(ClientePostRequestBody clientePostRequestBody) {
        Cliente cliente = Cliente.builder()
                .nome(clientePostRequestBody.getNome())
                .sobrenome(clientePostRequestBody.getSobrenome())
                .cpf(clientePostRequestBody.getCpf())
                .sexo(clientePostRequestBody.getSexo())
                .endereco(clientePostRequestBody.getEndereco())
                .contato(clientePostRequestBody.getContato())
                .statusCode(1)
                .dataCadastro(LocalDateTime.now())
                .build();
        return clienteRepository.save(cliente);
    }

    public void update(ClientePutRequestBody clientePutRequestBody) {
        Cliente cliente = findById(clientePutRequestBody.getId());
        cliente.setNome(clientePutRequestBody.getNome());
        cliente.setSobrenome(clientePutRequestBody.getSobrenome());
        cliente.setCpf(clientePutRequestBody.getCpf());
        cliente.setSexo(clientePutRequestBody.getSexo());
        cliente.setContato(clientePutRequestBody.getContato());
        cliente.setEndereco(clientePutRequestBody.getEndereco());
        clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = findById(id);
        cliente.setStatusCode(0);
        clienteRepository.save(cliente);
    }
}
