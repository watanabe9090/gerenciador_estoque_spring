package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.util.LocalCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for LocalRepository class")
public class LocalRepositoryTest {
    @Autowired
    private LocalRepository localRepository;

    @Test
    @DisplayName("Method: save - persists Local when Successful")
    void save_PersistsLocal_WhenSuccessful() {
        Local localToBeSaved = LocalCreator.createLocal();
        Local localSaved = this.localRepository.save(localToBeSaved);
        Assertions.assertThat(localSaved).isNotNull();
        Assertions.assertThat(localSaved.getId()).isNotNull();
        Assertions.assertThat(localSaved.getNome()).isEqualTo(localToBeSaved.getNome());
    }

    @Test
    @DisplayName("Method: save - updates Local when Successful")
    void save_UpdatesLocal_WhenSuccessful() {
        Local localToBeSaved = LocalCreator.createLocal();
        Local localSaved = this.localRepository.save(localToBeSaved);
        localSaved.setNome("Local Beta");
        Local localUpdated = this.localRepository.save(localSaved);
        Assertions.assertThat(localUpdated).isNotNull();
        Assertions.assertThat(localUpdated.getId()).isNotNull();
        Assertions.assertThat(localUpdated.getNome()).isEqualTo(localSaved.getNome());
    }

    @Test
    @DisplayName("Method: delete - removes Local when Successful")
    void delete_RemovesCliente_WhenSuccessful() {
        Local localToBeSaved = LocalCreator.createLocal();
        Local localSaved = this.localRepository.save(localToBeSaved);
        this.localRepository.delete(localSaved);
        Optional<Local> localOptional = this.localRepository.findById(localSaved.getId());
        Assertions.assertThat(localOptional).isEmpty();
    }

    /**
     * Tetsts for nome attribute
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is blank")
    void save_ThrowConstraintViolationException_WhenNomeIsBlank() {
        Local localToBeSaved = LocalCreator.createLocal();
        localToBeSaved.setNome("");
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> localRepository.save(localToBeSaved));
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is null")
    void save_ThrowConstraintViolationException_WhenNomeIsNull() {
        Local localToBeSaved = LocalCreator.createLocal();
        localToBeSaved.setNome(null);
        Assertions.assertThatThrownBy(() -> this.localRepository.save(localToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findBynome - returns list of Local when successful")
    void findByNome_ReturnsListOfLocal_WhenSuccessful() {
        Local localToBeSaved = LocalCreator.createLocal();
        Local localSaved = this.localRepository.save(localToBeSaved);
        String nome = localSaved.getNome();
        List<Local> locais = this.localRepository.findByNomeIgnoreCaseContaining(nome);
        Assertions.assertThat(locais)
                .isNotEmpty()
                .contains(localSaved);
    }
}
