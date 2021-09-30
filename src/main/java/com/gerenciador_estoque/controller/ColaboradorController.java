package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.colaborador.ColaboradorPostRequestBody;
import com.gerenciador_estoque.dto.colaborador.ColaboradorPutRequestBody;
import com.gerenciador_estoque.model.Colaborador;
import com.gerenciador_estoque.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("colaboradores")
@RequiredArgsConstructor
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @GetMapping
    public ResponseEntity<Page<Colaborador>> listAll(Pageable pageable) {
        return new ResponseEntity<>(colaboradorService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Colaborador> findById(@PathVariable Long id) {
        return new ResponseEntity<>(colaboradorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Colaborador> save(@RequestBody ColaboradorPostRequestBody colaboradorPostRequestBody) {
        return new ResponseEntity<>(colaboradorService.save(colaboradorPostRequestBody) ,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ColaboradorPutRequestBody colaboradorPutRequestBody) {
        colaboradorService.update(colaboradorPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        colaboradorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
