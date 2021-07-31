package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.model.Marca;
import com.gerenciador_estoque.model.Setor;
import com.gerenciador_estoque.util.FornecedorCreator;
import com.gerenciador_estoque.util.MarcaCreator;
import com.gerenciador_estoque.util.SetorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for MarcaRepository class")
public class MarcaRepositoryTest {

    @Autowired
    private MarcaRepository marcaRepository;

    @Test
    @DisplayName("Method: save - persists Marca when Successful")
    void save_PersistsMarca_WhenSuccessful() {
        Marca marcaToBeSaved = MarcaCreator.createMarca();
        Marca marcaSaved = this.marcaRepository.save(marcaToBeSaved);
        Assertions.assertThat(marcaSaved).isNotNull();
        Assertions.assertThat(marcaSaved.getId()).isNotNull();
        Assertions.assertThat(marcaSaved.getNome()).isEqualTo(marcaToBeSaved.getNome());
    }

    @Test
    @DisplayName("Method: save - updates Marca when Successful")
    void save_UpdatesMarca_WhenSuccessful() {
        Marca marcaToBeSaved = MarcaCreator.createMarca();
        Marca marcaSaved = this.marcaRepository.save(marcaToBeSaved);
        marcaSaved.setNome("Hello World");
        Marca marcaUpdated = this.marcaRepository.save(marcaSaved);
        Assertions.assertThat(marcaUpdated).isNotNull();
        Assertions.assertThat(marcaUpdated.getId()).isNotNull();
        Assertions.assertThat(marcaUpdated.getNome()).isEqualTo(marcaSaved.getNome());
    }

    @Test
    @DisplayName("Method: delete - removes Marca when Successful")
    void delete_RemovesMarca_WhenSuccessful() {
        Marca marcaToBeSaved = MarcaCreator.createMarca();
        Marca marcaSaved = this.marcaRepository.save(marcaToBeSaved);
        this.marcaRepository.delete(marcaSaved);
        Optional<Marca> marcaOptional = this.marcaRepository.findById(marcaSaved.getId());
        Assertions.assertThat(marcaOptional).isEmpty();
    }

    @Test
    @DisplayName("Method: findByFornecedor - returns list of Marca when Successful")
    void findByFornecedor_ReturnsListOfMarcas_WhenSuccessful() {
        Marca marcaToBeSaved = MarcaCreator.createMarca();
        Marca marcaSaved = this.marcaRepository.save(marcaToBeSaved);
        Fornecedor fornecedor = marcaSaved.getFornecedor();
        List<Marca> marcas = this.marcaRepository.findByFornecedor(fornecedor);
        Assertions.assertThat(marcas)
                .isNotEmpty()
                .contains(marcaSaved);
    }


    /**
     * Tests for 'nome' attribute
     * Cannot be null
     * Cannot be blank
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is blank")
    void save_ThrowConstraintViolationException_WhenNomeIsBlank() {
        Marca marcaToBeSaved = MarcaCreator.createMarca();
        marcaToBeSaved.setNome("");
        Assertions.assertThatThrownBy(() -> this.marcaRepository.save(marcaToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is null")
    void save_ThrowConstraintViolationException_WhenNomeIsNull() {
        Marca marcaToBeSaved = MarcaCreator.createMarca();
        marcaToBeSaved.setNome(null);
        Assertions.assertThatThrownBy(() -> this.marcaRepository.save(marcaToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Method: findByNomeIgnoreCaseContaining - returns list of Setores when Successful")
    void findByNomeIgnoreCaseContaining_ReturnsListOfSetores_WhenSuccessful() {
        Marca marcaToBeSaved = MarcaCreator.createMarca();
        Marca marcaSaved = this.marcaRepository.save(marcaToBeSaved);
        String nome = marcaSaved.getNome();
        List<Marca> marcas = this.marcaRepository.findByNomeIgnoreCaseContaining(nome);
        Assertions.assertThat(marcas)
                .isNotEmpty()
                .contains(marcaSaved);
    }



}
