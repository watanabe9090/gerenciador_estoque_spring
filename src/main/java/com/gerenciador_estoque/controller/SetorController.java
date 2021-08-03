package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.setor.SetorPostRequestBody;
import com.gerenciador_estoque.dto.setor.SetorPutRequestBody;
import com.gerenciador_estoque.model.Setor;
import com.gerenciador_estoque.service.SetorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(path = "setores")
@RequiredArgsConstructor
public class SetorController {
    private final SetorService setorService;

    @GetMapping
    public ResponseEntity<Page<Setor>> listAll(Pageable pageable) {
        return new ResponseEntity<>(setorService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Setor> findById(@PathVariable Long id) {
        return new ResponseEntity<>(setorService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Setor>> findByLocalId(@RequestParam Long localId) {
        return new ResponseEntity<>(setorService.findByLocalId(localId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Setor> save(@RequestBody @Valid SetorPostRequestBody setorPostRequestBody) {
        return new ResponseEntity<>(setorService.save(setorPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid SetorPutRequestBody setorPutRequestBody) {
        setorService.update(setorPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        setorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
