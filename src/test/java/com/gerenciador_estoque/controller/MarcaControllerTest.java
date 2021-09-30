package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.model.Marca;
import com.gerenciador_estoque.service.MarcaService;
import com.gerenciador_estoque.util.MarcaCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
public class MarcaControllerTest {
    @InjectMocks
    private MarcaController marcaController;
    @Mock
    private MarcaService marcaServiceMock;

    @BeforeEach
    void setUp() {
        PageImpl<Marca> marcaPage = new PageImpl<>(List.of(MarcaCreator.createMarca()));
        BDDMockito.when(marcaServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(marcaPage);
    }

    @Test
    @DisplayName("Method: listAll - returns list of Marca inside page Object when Successful")
    void listAll_ReturnsListOfMarcaInsidePageObject_WhenSuccessful() {
        String expectedNome = MarcaCreator.createMarca().getNome();
        Page<Marca> marcaPage = this.marcaController.listAll(null).getBody();
    }
}
