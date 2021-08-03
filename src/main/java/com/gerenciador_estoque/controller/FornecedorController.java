package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.fornecedor.FornecedorPostRequestBody;
import com.gerenciador_estoque.dto.fornecedor.FornecedorPutRequestBody;
import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.model.Marca;
import com.gerenciador_estoque.service.FornecedorService;
import com.gerenciador_estoque.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("fornecedores")
public class FornecedorController {
    private final FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<Page<Fornecedor>> listAll(Pageable pageable) {
        return new ResponseEntity<>(fornecedorService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        return new ResponseEntity<>(fornecedorService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Fornecedor>> findByNomeFantasia(@RequestParam String nomeFantasia) {
        return new ResponseEntity<>(fornecedorService.findByNomeFantasia(nomeFantasia), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> save(@RequestBody @Valid FornecedorPostRequestBody fornecedorPostRequestBody) {
        return new ResponseEntity<>(fornecedorService.save(fornecedorPostRequestBody), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody FornecedorPutRequestBody fornecedorPutRequestBody) {
        System.out.println(fornecedorPutRequestBody.toString());
        fornecedorService.update(fornecedorPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fornecedorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
