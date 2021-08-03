package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.marca.MarcaPostRequestBody;
import com.gerenciador_estoque.dto.marca.MarcaPutRequestBody;
import com.gerenciador_estoque.model.Marca;
import com.gerenciador_estoque.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(path = "marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<Page<Marca>> listAll(Pageable pageable) {
        return new ResponseEntity<>(marcaService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Marca> findById(@PathVariable Long id) {
        return new ResponseEntity<>(marcaService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Marca>> findByFornecedor(@RequestParam Long fornecedorId) {
        return new ResponseEntity<>(marcaService.findByFornecedor(fornecedorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Marca> save(@RequestBody MarcaPostRequestBody marcaPostRequestBody) {
        return new ResponseEntity<>(marcaService.save(marcaPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody MarcaPutRequestBody marcaPutRequestBody) {
        marcaService.update(marcaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        marcaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
