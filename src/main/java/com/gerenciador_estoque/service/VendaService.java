package com.gerenciador_estoque.service;

import com.gerenciador_estoque.dto.venda.ItemVendidoPostRequestBody;
import com.gerenciador_estoque.dto.venda.VendaDetail;
import com.gerenciador_estoque.dto.venda.VendaPostRequestBody;
import com.gerenciador_estoque.exception.BadRequestException;
import com.gerenciador_estoque.model.ItemEstocado;
import com.gerenciador_estoque.model.ItemVendido;
import com.gerenciador_estoque.model.Venda;
import com.gerenciador_estoque.repository.ItemEstocadoRepository;
import com.gerenciador_estoque.repository.ItemVendidoRepository;
import com.gerenciador_estoque.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendidoRepository itemVendidoRepository;
    private final ItemEstocadoRepository itemEstocadoRepository;

    public Page<Venda> listAll(Pageable pageable) {
        return vendaRepository.findAll(pageable);
    }

    public Venda findByIdOrThrowBadRequestException(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Venda não encontrada"));
    }

    public VendaDetail findById(Long id) {
        Venda venda = findByIdOrThrowBadRequestException(id);
        VendaDetail vendaDetail = VendaDetail.builder()
                .vendaId(venda.getId())
                .valor(venda.getValor())
                .itensVendidos(itemVendidoRepository.findByVenda(venda))
                .dataCadastro(venda.getDataCadastro())
                .build();
        System.out.println(vendaDetail.getItensVendidos().toString());
        return vendaDetail;
    }

    @Transactional
    public Venda save(VendaPostRequestBody vendaPostRequestBody) {
        Venda venda = Venda.builder()
                .valor(vendaPostRequestBody.getValor())
                .dataCadastro(LocalDateTime.now())
                .build();
        venda = vendaRepository.save(venda);

        for(var i : vendaPostRequestBody.getItensVendidos()) {
            ItemEstocado itemEstocado = itemEstocadoRepository.getById(i.getItemEstocadoId());
            itemEstocado.setQuantidade(itemEstocado.getQuantidade() - i.getQuantidade());
            ItemVendido itemVendido = ItemVendido.builder()
                    .itemEstocado(itemEstocado)
                    .quantidade(i.getQuantidade())
                    .venda(venda)
                    .build();
            System.out.println(itemVendidoRepository.save(itemVendido));
        }
        return venda;
    }
}
