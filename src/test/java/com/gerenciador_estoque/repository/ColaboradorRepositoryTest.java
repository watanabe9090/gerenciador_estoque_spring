package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Colaborador;
import com.gerenciador_estoque.util.ColaboradorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for ColaboradorRepository class")
public class ColaboradorRepositoryTest {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Test
    @DisplayName("Method: save - persists Colaborador when Successful")
    void save_PersistsColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        Assertions.assertThat(colaboradorSaved).isNotNull();
        Assertions.assertThat(colaboradorSaved.getId()).isNotNull();
        Assertions.assertThat(colaboradorSaved.getNome()).isEqualTo(colaboradorToBeSaved.getNome());
    }

    @Test
    @DisplayName("Method: save - updates Colaborador when Successful")
    void save_UpdatesColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        colaboradorSaved.setNome("Some different Nome");
        Colaborador colaboradorUpdated = this.colaboradorRepository.save(colaboradorSaved);
        Assertions.assertThat(colaboradorUpdated).isNotNull();
        Assertions.assertThat(colaboradorUpdated.getId()).isNotNull();
        Assertions.assertThat(colaboradorUpdated.getNome()).isEqualTo(colaboradorSaved.getNome());
    }

    @Test
    @DisplayName("Method: delete - removes Colaborador when Successful")
    void delete_RemovesColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        this.colaboradorRepository.delete(colaboradorSaved);
        Optional<Colaborador> colaboradorOptional = this.colaboradorRepository.findById(colaboradorSaved.getId());
        Assertions.assertThat(colaboradorOptional).isEmpty();
    }


    /**
     * Tests for 'nome' attribute
     * Cannot be null
     * Cannot be blank
     * Just letters
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is blank")
    void save_ThrowConstraintViolationException_WhenNomeIsBlank() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setNome("");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is null")
    void save_ThrowConstraintViolationException_WhenNomeIsNull() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setNome(null);
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome contains digits")
    void save_ThrowConstraintViolationException_WhenNomeContainsLetters() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setNome("Dagob3rto");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findByNomeIgnoreCaseContaining - returns list of Colaborador when successful")
    void findByNomeIgnoreCaseContaining_ReturnsListOfColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String nome = colaboradorSaved.getNome();
        List<Colaborador> colaboradores = this.colaboradorRepository.findByNomeIgnoreCaseContaining(nome);
        Assertions.assertThat(colaboradores)
                .isNotEmpty()
                .contains(colaboradorSaved);
    }
    @Test
    @DisplayName("Method: findByNomeIgnoreCaseContaining - returns empty list of Colaborador searched by nome when any Colaborador not founded")
    void findByNomeIgnoreCaseContaining_ReturnsEmptyListOfColaborador_WhenColaboradorNotFound() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String nome = "SomeNastyName";
        List<Colaborador> colaboradores = this.colaboradorRepository.findByNomeIgnoreCaseContaining(nome);
        Assertions.assertThat(colaboradores)
                .doesNotContain(colaboradorSaved)
                .isEmpty();
    }

    /**
     * Tests for 'sobrenome' attribute
     * Cannot be null
     * Cannot be blank
     * Just letters and blank space
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sobrenome is blank")
    void save_ThrowConstraintViolationException_WhenSobrenomeIsBlank() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSobrenome("");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sobrenome is null")
    void save_ThrowConstraintViolationException_WhenSobrenomeIsNull() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSobrenome(null);
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sobrenome contains digits")
    void save_ThrowConstraintViolationException_WhenSobrenomeContainsLetters() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSobrenome("Silv3ra");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findBySobrenomeIgnoreCaseContaining - returns list of Colaborador  when successful")
    void findBySobrenomeIgnoreCaseContaining_ReturnsListOfColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String sobrenome = colaboradorSaved.getSobrenome();
        List<Colaborador> colaboradores = this.colaboradorRepository.findBySobrenomeIgnoreCaseContaining(sobrenome);
        Assertions.assertThat(colaboradores)
                .isNotEmpty()
                .contains(colaboradorSaved);
    }
    @Test
    @DisplayName("Method: findBySobrenomeIgnoreCaseContaining - returns empty list of Colaborador searched by sobrenome when any Colaborador not founded")
    void findBySobrenomeIgnoreCaseContaining_ReturnsEmptyListOfColaborador_WhenColaboradorNotFound() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String sobrenome = "SomeNastySobrenome";
        List<Colaborador> colaboradores = this.colaboradorRepository.findByNomeIgnoreCaseContaining(sobrenome);
        Assertions.assertThat(colaboradores)
                .doesNotContain(colaboradorSaved)
                .isEmpty();

    }


    /**
     * Tests for 'sexo' attribute
     * Cannot be null
     * Cannot be blank
     * length = 1
     * Just values: m,f,o,M,F,O
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo is null")
    void save_ThrowConstraintViolationException_WhenSexoIsNull() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSexo(null);
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo is blank")
    void save_ThrowConstraintViolationException_WhenSexoIsBlank() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSexo("");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo have length greater than 1")
    void save_ThrowConstraintViolationException_WhenSexoLengthGreaterThan1() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSexo("ff");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo is digit")
    void save_ThrowConstraintViolationException_WhenSexoIsDigit() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSexo("1");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo isn't any of: mfoMFO ")
    void save_ThrowConstraintViolationException_WhenSexoValueIsntValid() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setSexo("t");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Method: findBySexoIgnoreCase - returns list of Colaborador when successful")
    void findBySexo_ReturnsListOfColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String sexo = colaboradorSaved.getSexo();
        List<Colaborador> colaboradores = this.colaboradorRepository.findBySexoIgnoreCase(sexo);
        Assertions.assertThat(colaboradores)
                .isNotEmpty()
                .contains(colaboradorSaved);
    }
    @Test
    @DisplayName("Method: findBySexoIgnoreCase - returns empty list of Colaborador searched by sexo when any Colaborador not founded")
    void findBySexoIgnoreCase_ReturnsEmptyListOfColaborador_WhenColaboradorNotFound() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String sexo = "o";
        List<Colaborador> colaboradores = this.colaboradorRepository.findBySexoIgnoreCase(sexo);
        Assertions.assertThat(colaboradores)
                .doesNotContain(colaboradorSaved)
                .isEmpty();
    }


    /**
     * Tests for 'cpf' attribute
     * Cannot be null
     * Cannot be blank
     * length = 11
     * Must have only digits
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf is blank")
    void save_ThrowConstraintViolationException_WhenCpfIsBlank() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setCpf("");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf is null")
    void save_ThrowConstraintViolationException_WhenCpfIsNull() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setCpf(null);
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf contains letters")
    void save_ThrowConstraintViolationException_WhenCpfContainLetters() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setCpf("9993337772a");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf lenght are greater than 11")
    void save_ThrowConstraintViolationException_WhenCpfLengthGreaterThan11() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setCpf("99900099911000");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf lenght are less than 11")
    void save_ThrowConstraintViolationException_WhenCpfLengthLessThan11() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setCpf("9990");
        Assertions.assertThatThrownBy(() -> colaboradorRepository.save(colaboradorToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Method: findByCpfContaining - returns list of Colaborador when successful")
    void findByCpfContaining_ReturnsListOfColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String cpf = colaboradorSaved.getCpf();
        List<Colaborador> colaboradores = this.colaboradorRepository.findByCpfContaining(cpf);
        Assertions.assertThat(colaboradores)
                .isNotEmpty()
                .contains(colaboradorSaved);
    }

    @Test
    @DisplayName("Method: findByCpfContaining - returns empty list of Colaborador searched by cpf when any Colaborador not founded")
    void findByCpfContaining_ReturnsEmptyListOfColaborador_WhenColaboradorNotFound() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        String cpf = "00000000000";
        List<Colaborador> colaboradores = this.colaboradorRepository.findBySexoIgnoreCase(cpf);
        Assertions.assertThat(colaboradores)
                .doesNotContain(colaboradorSaved)
                .isEmpty();
    }


    /**
     * Tests for 'dataNascimento' attribute
     * Cannot be null
     */

    @Test
    @DisplayName("Method: findByDataNascimentoBetween - return List of Colaborador when successful")
    void findByDataNascimentoBetween_ReturnsListOfColaborador_WhenSuccessful() {
        Colaborador colaboradorToBeSaved = ColaboradorCreator.createColaborador();
        colaboradorToBeSaved.setDataNascimento(LocalDate.of(2000, 10, 10));
        Colaborador colaboradorSaved = this.colaboradorRepository.save(colaboradorToBeSaved);
        LocalDate localDateStart = colaboradorSaved.getDataNascimento();
        LocalDate localDateEnd = LocalDate.now();
        List<Colaborador> colaboradores = this.colaboradorRepository.findByDataNascimentoBetween(localDateStart, localDateEnd);
        Assertions.assertThat(colaboradores)
                .isNotEmpty()
                .contains(colaboradorSaved);
    }

}
