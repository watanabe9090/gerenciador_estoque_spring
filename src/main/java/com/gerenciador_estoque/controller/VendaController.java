package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.venda.VendaDetail;
import com.gerenciador_estoque.dto.venda.VendaPostRequestBody;
import com.gerenciador_estoque.model.Venda;
import com.gerenciador_estoque.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService vendaService;

    @GetMapping
    public ResponseEntity<Page<Venda>> listAll(Pageable pageable) {
        return new ResponseEntity<>(vendaService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDetail> findById(@PathVariable Long id) {
        return new ResponseEntity<>(vendaService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Venda> save(@RequestBody VendaPostRequestBody vendaPostRequestBody) {
        return new ResponseEntity<>(vendaService.save(vendaPostRequestBody), HttpStatus.OK);
    }

}
