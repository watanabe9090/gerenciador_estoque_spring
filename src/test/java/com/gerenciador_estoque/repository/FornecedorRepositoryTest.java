package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Fornecedor;
import com.gerenciador_estoque.util.FornecedorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for FornecedorRepository class")
public class FornecedorRepositoryTest {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Test
    @DisplayName("Method: save - persists Fornecedor when Successful")
    void save_PersistsFornecedor_WhenSuccessful() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = this.fornecedorRepository.save(fornecedorToBeSaved);
        Assertions.assertThat(fornecedorSaved).isNotNull();
        Assertions.assertThat(fornecedorSaved.getId()).isNotNull();
        Assertions.assertThat(fornecedorSaved.getNomeFantasia()).isEqualTo(fornecedorToBeSaved.getNomeFantasia());
    }

    @Test
    @DisplayName("Method: save - updates Fornecedor when Successful")
    void save_UpdatesFornecedor_WhenSuccessful() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = this.fornecedorRepository.save(fornecedorToBeSaved);
        fornecedorSaved.setNomeFantasia("New Nome Fantasia");
        Fornecedor fornecedorUpdated = fornecedorRepository.save(fornecedorSaved);
        Assertions.assertThat(fornecedorUpdated).isNotNull();
        Assertions.assertThat(fornecedorUpdated.getId()).isNotNull();
        Assertions.assertThat(fornecedorUpdated.getNomeFantasia()).isEqualTo(fornecedorSaved.getNomeFantasia());
    }

    @Test
    @DisplayName("Method: delete - removes Fornecedor when Successful")
    void delete_RemovesFornecedor_WhenSuccessful() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = this.fornecedorRepository.save(fornecedorToBeSaved);
        fornecedorRepository.delete(fornecedorSaved);
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(fornecedorSaved.getId());
        Assertions.assertThat(fornecedorOptional).isEmpty();
    }

    /**
     * Tests for 'nomeFantasia' Attribute
     * Cannot be blank
     * Cannot be null
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nomeFantasia is blank")
    void save_ThrowConstraintViolationException_WhenNomeFantasiaIsEmpty() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        fornecedorToBeSaved.setNomeFantasia("");
        Assertions.assertThatThrownBy(() -> fornecedorRepository.save(fornecedorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nomeFantasia is null")
    void save_ThrowConstraintViolationException_WhenNomeFantasiaIsNull() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        fornecedorToBeSaved.setNomeFantasia(null);
        Assertions.assertThatThrownBy(() -> fornecedorRepository.save(fornecedorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findByNomeFantasiaIgnoreCaseContaining - returns list of Fornecedor when Successful")
    void findByNomeFantasiaIgnoreCaseContaining_ReturnsListOfFornecedor_WhenSuccessful() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = this.fornecedorRepository.save(fornecedorToBeSaved);
        String nomeFantasia = fornecedorSaved.getNomeFantasia();
        List<Fornecedor> fornecedores = this.fornecedorRepository.findByNomeFantasiaIgnoreCaseContaining(nomeFantasia);
        Assertions.assertThat(fornecedores)
                .isNotEmpty()
                .contains(fornecedorSaved);
    }
    @Test
    @DisplayName("Method: findByNomeFantasia - returns empty list of Fornecedor searched by nomeFantasia when Fornecedor not found")
    void findByNomeFantasia_ReturnsEmptyListOfFornecedor_WhenFornecedorNotFound() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = this.fornecedorRepository.save(fornecedorToBeSaved);
        String nomeFantasia = "A different nomeFantasia";
        List<Fornecedor> fornecedores = this.fornecedorRepository.findByNomeFantasiaIgnoreCaseContaining(nomeFantasia);
        Assertions.assertThat(fornecedores).isEmpty();
        Assertions.assertThat(fornecedores).doesNotContain(fornecedorSaved);
    }

    /**
     * Tests for 'cnpj' Attribute
     * Cannot be null
     * Cannot be blank
     * Cannot contain letters
     * Length = 14
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cnpj is blank")
    void save_ThrowConstraintViolationException_WhenCnpjIsEmpty() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        fornecedorToBeSaved.setCnpj("");
        Assertions.assertThatThrownBy(() -> fornecedorRepository.save(fornecedorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cnpj is null")
    void save_ThrowConstraintViolationException_WhenCnpjIsNull() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        fornecedorToBeSaved.setCnpj(null);
        Assertions.assertThatThrownBy(() -> fornecedorRepository.save(fornecedorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findByCnpjContaining - returns list of Fornecedor when Successful")
    void findByCnpjContaining_ReturnsListOfFornecedor_WhenSuccessful() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = fornecedorRepository.save(fornecedorToBeSaved);
        String cnpj = fornecedorSaved.getCnpj();
        List<Fornecedor> fornecedores = fornecedorRepository.findByCnpjContaining(cnpj);
        Assertions.assertThat(fornecedores)
                .isNotEmpty()
                .contains(fornecedorSaved);
    }
    @Test
    @DisplayName("Method: findByCnpjContaining - returns empty list of Fornecedor by cnpj when Fornecedor not found")
    void findByCnpjIgnoreCaseContaining_ReturnsEmptyListOfFornecedor_WhenFornecedorNotFound() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = this.fornecedorRepository.save(fornecedorToBeSaved);
        String cnpj = "11111111111111";
        List<Fornecedor> fornecedores = this.fornecedorRepository.findByCnpjContaining(cnpj);
        Assertions.assertThat(fornecedores)
                .doesNotContain(fornecedorSaved)
                .isEmpty();
    }


    /**
     * Tests for 'razaoSocial' Attribute
     * Cannot be null
     * Cannot be blank
     * Just letters
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when razaoSocial is blank")
    void save_ThrowConstraintViolationException_WhenRazaoSocialIsEmpty() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        fornecedorToBeSaved.setNomeFantasia("");
        Assertions.assertThatThrownBy(() -> fornecedorRepository.save(fornecedorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when razaoSocial is null")
    void save_ThrowConstraintViolationException_WhenRazaoSocialIsNull() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        fornecedorToBeSaved.setRazaoSocial(null);
        Assertions.assertThatThrownBy(() -> fornecedorRepository.save(fornecedorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when razaoSocial is null")
    void save_ThrowConstraintViolationException_WhenRazaoSocialContainNotCharacters() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        fornecedorToBeSaved.setRazaoSocial("Luca@s ALm3ida");
        Assertions.assertThatThrownBy(() -> fornecedorRepository.save(fornecedorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findByRazaoSocialIgnoreCaseContaining - returns list of Fornecedor when Successful")
    void findByRazaoSocialIgnoreCaseContaining_ReturnsListOfFornecedor_WhenSuccessful() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = fornecedorRepository.save(fornecedorToBeSaved);
        String cnpj = fornecedorSaved.getCnpj();
        List<Fornecedor> fornecedores = fornecedorRepository.findByCnpjContaining(cnpj);
        Assertions.assertThat(fornecedores)
                .isNotEmpty()
                .contains(fornecedorSaved);
    }
    @Test
    @DisplayName("Method: findByRazaoSocialIgnoreCaseContaining - returns empty list of Fornecedor by nomeFantasia when Fornecedor not found")
    void findByRazaoSocialIgnoreCaseContaining_ReturnsEmptyListOfFornecedor_WhenFornecedorNotFound() {
        Fornecedor fornecedorToBeSaved = FornecedorCreator.createFornecedor();
        Fornecedor fornecedorSaved = this.fornecedorRepository.save(fornecedorToBeSaved);
        String cnpj = "A different razaoSocial";
        List<Fornecedor> fornecedores = this.fornecedorRepository.findByNomeFantasiaIgnoreCaseContaining(cnpj);
        Assertions.assertThat(fornecedores)
                .doesNotContain(fornecedorSaved)
                .isEmpty();
    }

}
