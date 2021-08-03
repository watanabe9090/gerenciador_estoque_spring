package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.local.LocalPostRequestBody;
import com.gerenciador_estoque.dto.local.LocalPutRequestBody;
import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.service.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("locais")
@RequiredArgsConstructor
public class LocalController {

    private final LocalService localService;

    @GetMapping
    public ResponseEntity<Page<Local>> listAll(Pageable pageable) {
        return ResponseEntity.ok(localService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Local> listAll(@PathVariable Long id) {
        return new ResponseEntity<>(localService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Local> save(@RequestBody LocalPostRequestBody localPostRequestBody) {
        return ResponseEntity.ok(localService.save(localPostRequestBody));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody LocalPutRequestBody localPutRequestBody) {
        localService.update(localPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        localService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
