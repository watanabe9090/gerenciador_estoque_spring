package com.gerenciador_estoque.controller;


import com.gerenciador_estoque.dto.itemEstocado.ItemEstocadoList;
import com.gerenciador_estoque.dto.itemEstocado.ItemEstocadoGetSingleRequestBody;
import com.gerenciador_estoque.dto.itemEstocado.ItemEstocadoPostRequestBody;
import com.gerenciador_estoque.model.ItemEstocado;
import com.gerenciador_estoque.service.ItemEstocadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "itens_estocados")
@RestController
@RequiredArgsConstructor
public class ItemEstocadoController {
    private final ItemEstocadoService itemEstocadoService;
    @GetMapping
    public ResponseEntity<Page<ItemEstocado>> listAll(Pageable pageable) {
        return new ResponseEntity<>(itemEstocadoService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ItemEstocadoList>> listAllForVenda() {
        return new ResponseEntity<>(itemEstocadoService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemEstocadoGetSingleRequestBody> findById(@PathVariable Long id) {
        return new ResponseEntity<>(itemEstocadoService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemEstocado> save(@RequestBody ItemEstocadoPostRequestBody itemEstocadoPostRequestBody) {
        System.out.println(itemEstocadoPostRequestBody.toString());
        return new ResponseEntity<>(itemEstocadoService.save(itemEstocadoPostRequestBody), HttpStatus.OK);
    }

}
