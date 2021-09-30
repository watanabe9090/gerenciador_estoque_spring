package com.gerenciador_estoque.service;

import com.gerenciador_estoque.model.Lote;
import com.gerenciador_estoque.repository.LoteRepository;
import com.gerenciador_estoque.util.LoteCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class LoteServiceTest {
    @InjectMocks
    private LoteService loteService;
    @Mock
    private LoteRepository loteRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<Lote> lotePage = new PageImpl<>(List.of(LoteCreator.createLote()));
        BDDMockito.when(loteRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(lotePage);
        BDDMockito.when(loteRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(LoteCreator.createLote()));
    }
}
