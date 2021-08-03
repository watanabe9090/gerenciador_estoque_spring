package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.cliente.ClientePostRequestBody;
import com.gerenciador_estoque.dto.cliente.ClientePutRequestBody;
import com.gerenciador_estoque.model.Cliente;
import com.gerenciador_estoque.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> listAll(Pageable pageable) {
        return new ResponseEntity(clienteService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.findById(id), HttpStatus.OK);
    }
    @GetMapping(path = "/find")
    public ResponseEntity<Page<Cliente>> findByRequestParams(
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "") String sobrenome,
            @RequestParam(required = false, defaultValue = "") String cpf,
            @RequestParam(required = false, defaultValue = "") String sexo,
            Pageable pageable) {
        if(!nome.isEmpty()) {
            return new ResponseEntity<>(clienteService.findByNome(nome), HttpStatus.OK);
        }
        if(!sobrenome.isEmpty()) {
            return new ResponseEntity<>(clienteService.findBySobrenome(sobrenome), HttpStatus.OK);
        }
        if(!cpf.isEmpty()) {
            return new ResponseEntity<>(clienteService.findByCpf(cpf), HttpStatus.OK);
        }
        return listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClientePostRequestBody clientePostRequestBody) {
        return new ResponseEntity(clienteService.save(clientePostRequestBody), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid ClientePutRequestBody clientePutRequestBody) {
        clienteService.update(clientePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}