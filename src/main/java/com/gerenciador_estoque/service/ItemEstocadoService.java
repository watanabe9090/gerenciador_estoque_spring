package com.gerenciador_estoque.service;

import com.gerenciador_estoque.dto.itemEstocado.ItemEstocadoGetSingleRequestBody;
import com.gerenciador_estoque.dto.itemEstocado.ItemEstocadoPostRequestBody;
import com.gerenciador_estoque.exception.BadRequestException;
import com.gerenciador_estoque.model.*;
import com.gerenciador_estoque.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ItemEstocadoService {
    private final ItemEstocadoRepository itemEstocadoRepository;
    private final SetorRepository setorRepository;
    private final MarcaRepository marcaRepository;
    private final LoteRepository loteRepository;
    private final MercadoriaRepository mercadoriaRepository;

    public Page<ItemEstocado> listAll(Pageable pageable) {
        return itemEstocadoRepository.findAll(pageable);
    }

    private ItemEstocado findyId(Long id) {
        ItemEstocado itemEstocado = itemEstocadoRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Item Estocado não encontrado"));
        return itemEstocado;
    }

    public ItemEstocadoGetSingleRequestBody findById(Long id) {
        ItemEstocado itemEstocado = findyId(id);
        ItemEstocadoGetSingleRequestBody requestBody = ItemEstocadoGetSingleRequestBody.builder()
                .codigo(itemEstocado.getCodigo())
                .nome(itemEstocado.getMercadoria().getNome())
                .dataFabricacao(itemEstocado.getLote().getDataFabricacao())
                .dataVencimento(itemEstocado.getLote().getDataVencimento())
                .unidade(itemEstocado.getMercadoria().getUnidade())
                .precoCompra(itemEstocado.getPrecoCompra())
                .precoVenda(itemEstocado.getPrecoVenda())
                .descricao(itemEstocado.getMercadoria().getDescricao())
                .descricaoReduzida(itemEstocado.getMercadoria().getDescricaoReduzida())
                .fornecedorNomeFantasia(itemEstocado.getMercadoria().getMarca().getFornecedor().getNomeFantasia())
                .marcaNome(itemEstocado.getMarca().getNome())
                .localNome(itemEstocado.getSetor().getLocal().getNome())
                .setorNome(itemEstocado.getSetor().getNome())
                .build();
        return requestBody;
    }

    public ItemEstocado save(ItemEstocadoPostRequestBody itemEstocadoPostRequestBody) {
        Setor setor = setorRepository.findById(itemEstocadoPostRequestBody.getSetorId()).get();
        Marca marca = marcaRepository.findById(itemEstocadoPostRequestBody.getMarcaId()).get();
        Mercadoria mercadoria = Mercadoria.builder()
                .nome(itemEstocadoPostRequestBody.getNome())
                .descricao(itemEstocadoPostRequestBody.getDescricao())
                .unidade(itemEstocadoPostRequestBody.getUnidade())
                .descricaoReduzida(itemEstocadoPostRequestBody.getDescricaoReduzida())
                .marca(marca)
                .build();
        mercadoria = mercadoriaRepository.save(mercadoria);
        Lote lote = Lote.builder()
                .dataFabricacao(itemEstocadoPostRequestBody.getDataFabricacao())
                .dataVencimento(itemEstocadoPostRequestBody.getDataVencimento())
                .build();
        lote = loteRepository.save(lote);

        ItemEstocado itemEstocado = ItemEstocado.builder()
                .mercadoria(mercadoria)
                .codigo(itemEstocadoPostRequestBody.getCodigo())
                .lote(lote)
                .marca(marca)
                .setor(setor)
                .codigo(itemEstocadoPostRequestBody.getCodigo())
                .precoCompra(itemEstocadoPostRequestBody.getPrecoCompra())
                .precoVenda(itemEstocadoPostRequestBody.getPrecoVenda())
                .dataCadastro(LocalDateTime.now())
                .statusCode(1)
                .build();
        return itemEstocadoRepository.save(itemEstocado);
    }


}
