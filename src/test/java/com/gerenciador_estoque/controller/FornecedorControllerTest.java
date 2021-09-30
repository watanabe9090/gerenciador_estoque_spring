package com.gerenciador_estoque.controller;

import com.gerenciador_estoque.dto.fornecedor.FornecedorPostRequestBody;
import com.gerenciador_estoque.dto.fornecedor.FornecedorPutRequestBody;
import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.service.FornecedorService;
import com.gerenciador_estoque.util.FornecedorCreator;
import com.gerenciador_estoque.util.FornecedorPostRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class FornecedorControllerTest {
    @InjectMocks
    private FornecedorController fornecedorController;
    @Mock
    private FornecedorService fornecedorServiceMock;
    @BeforeEach
    void setUp() {
        Fornecedor fornecedor = FornecedorCreator.createFornecedor();
        fornecedor.setId(1L);
        PageImpl<Fornecedor> fornecedorPage = new PageImpl<>(List.of(fornecedor));
        BDDMockito.when(fornecedorServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(fornecedorPage);

        BDDMockito.when(fornecedorServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(fornecedor);

        BDDMockito.when(fornecedorServiceMock.findByNomeFantasia(ArgumentMatchers.anyString()))
                .thenReturn(List.of(fornecedor));

        BDDMockito.when(fornecedorServiceMock.save(ArgumentMatchers.any(FornecedorPostRequestBody.class)))
                .thenReturn(fornecedor);

        BDDMockito.doNothing().when(fornecedorServiceMock).update(ArgumentMatchers.any(FornecedorPutRequestBody.class));

    }

    @Test
    @DisplayName("Method: listAll - returns list of Fornecedor inside page object when sucessful")
    void listAll_ReturnsListOfFornecedoresInsidePageObject_WhenSuccessful() {
        String expectedNomeFantasia = FornecedorCreator.createFornecedor().getNomeFantasia();
        Page<Fornecedor> fornecedorPage = fornecedorController.listAll(null).getBody();
        Assertions.assertThat(fornecedorPage).isNotNull();
        Assertions.assertThat(fornecedorPage.toList()).isNotEmpty().hasSize(1);
        Assertions.assertThat(fornecedorPage.toList().get(0).getNomeFantasia()).isEqualTo(expectedNomeFantasia);
    }

    @Test
    @DisplayName("Method: findById - returns Fornecedor when sucessful")
    void findById_ReturnsFornecedor_WhenSuccessful() {
        Long expectedId = 1L;
        Fornecedor fornecedor = fornecedorController.findById(1L).getBody();
        Assertions.assertThat(fornecedor).isNotNull();
        Assertions.assertThat(fornecedor.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("Method: findByNomeFantasia - returns list of Fornecedor when sucessful")
    void findByNomeFantasia_ReturnsFornecedor_WhenSuccessful(){
        String expectedName = FornecedorCreator.createFornecedor().getNomeFantasia();
        List<Fornecedor> fornecedores = fornecedorController.findByNomeFantasia(expectedName).getBody();
        Assertions.assertThat(fornecedores)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(fornecedores.get(0).getNomeFantasia()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Method: findByNomeFantasia - returns an empty list of Fornecedor when fornecedor is not found")
    void findByNomeFantasia_ReturnsEmptyListOfFornecedor_WhenFornecedorIsNotFound(){
        BDDMockito.when(fornecedorServiceMock.findByNomeFantasia(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());
        List<Fornecedor> fornecedores = fornecedorController.findByNomeFantasia("some someFantasia").getBody();
        Assertions.assertThat(fornecedores)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Method: save - returns Fornecedor when sucessful")
    void save_ReturnsFornecedor_WhenSuccessful() {
        Fornecedor fornecedor = fornecedorController.save(FornecedorPostRequestBodyCreator.createFornecedorPostRequestBody()).getBody();
        Assertions.assertThat(fornecedor)
                .isNotNull()
                .isEqualTo(FornecedorCreator.createFornecedor());
    }

}
