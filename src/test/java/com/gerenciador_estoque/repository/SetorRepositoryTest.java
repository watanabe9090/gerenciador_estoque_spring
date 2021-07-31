package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Local;
import com.gerenciador_estoque.model.Setor;
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
@DisplayName("Tests for SetorRepository class ")
class SetorRepositoryTest {
    @Autowired
    private SetorRepository setorRepository;

    @Test
    @DisplayName("Method: save - persists Setor when Successful")
    void save_PersistsSetor_WhenSuccessful() {
        Setor setorToBeSaved = SetorCreator.createSetor();
        Setor setorSaved = this.setorRepository.save(setorToBeSaved);
        Assertions.assertThat(setorSaved).isNotNull();
        Assertions.assertThat(setorSaved.getId()).isNotNull();
        Assertions.assertThat(setorSaved.getNome()).isEqualTo(setorToBeSaved.getNome());
    }
    @Test
    @DisplayName("Method: save - updates Setor when Successful")
    void save_UpdateSetor_WhenSuccessful() {
        Setor setorToBeSaved = SetorCreator.createSetor();
        Setor setorSaved = this.setorRepository.save(setorToBeSaved);
        setorSaved.setNome("Setor Z");
        Setor setorUpdated = this.setorRepository.save(setorSaved);
        Assertions.assertThat(setorUpdated).isNotNull();
        Assertions.assertThat(setorUpdated.getId()).isNotNull();
        Assertions.assertThat(setorUpdated.getNome()).isEqualTo(setorSaved.getNome());
    }
    @Test
    @DisplayName("Method: delete - removes Setor when Successful")
    void delete_RemoveSetor_WhenSuccessful() {
        Setor setorToBeSaved = SetorCreator.createSetor();
        Setor setorSaved = this.setorRepository.save(setorToBeSaved);
        this.setorRepository.delete(setorSaved);
        Optional<Setor> setorOptional = this.setorRepository.findById(setorSaved.getId());
        Assertions.assertThat(setorOptional).isEmpty();
    }
    @Test
    @DisplayName("Method: findByLocal - returns list of Setores when Successful")
    void findByLocal_ReturnsListOfSetores_WhenSuccessful() {
        Setor setorToBeSaved = SetorCreator.createSetor();
        Setor setorSaved = this.setorRepository.save(setorToBeSaved);
        Local local = setorSaved.getLocal();
        List<Setor> setores = this.setorRepository.findByLocal(local);
        Assertions.assertThat(setores)
                .isNotEmpty()
                .contains(setorSaved);
    }

    /**
     * Tests for 'nome' attribute
     * Cannot be null
     * Cannot be blank
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is blank")
    void save_ThrowConstraintViolationException_WhenNomeIsBlank() {
        Setor setorToBeSaved = SetorCreator.createSetor();
        setorToBeSaved.setNome("");
        Assertions.assertThatThrownBy(() -> this.setorRepository.save(setorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is null")
    void save_ThrowConstraintViolationException_WhenNomeIsNull() {
        Setor setorToBeSaved = SetorCreator.createSetor();
        setorToBeSaved.setNome(null);
        Assertions.assertThatThrownBy(() -> this.setorRepository.save(setorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Method: findByNomeIgnoreCaseContaining - returns list of Setores when Successful")
    void findByNomeIgnoreCaseContaining_ReturnsListOfSetores_WhenSuccessful() {
        Setor setorToBeSaved = SetorCreator.createSetor();
        Setor setorSaved = this.setorRepository.save(setorToBeSaved);
        String nome = setorSaved.getNome();
        List<Setor> setores = this.setorRepository.findByNomeIgnoreCaseContaining(nome);
        Assertions.assertThat(setores)
                .isNotEmpty()
                .contains(setorSaved);
    }

}
