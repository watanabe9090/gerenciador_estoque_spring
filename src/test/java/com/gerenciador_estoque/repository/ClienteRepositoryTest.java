package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Cliente;
import com.gerenciador_estoque.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for ClienteRepository class")
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Method: save - persists Cliente when Successful")
    void save_PersistsCliente_WhenSuccessful() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        Assertions.assertThat(clienteSaved).isNotNull();
        Assertions.assertThat(clienteSaved.getId()).isNotNull();
        Assertions.assertThat(clienteSaved.getNome()).isEqualTo(clienteToBeSaved.getNome());
    }

    @Test
    @DisplayName("Method: save - updates Cliente when Successful")
    void save_UpdateCliente_WhenSuccessful() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        clienteSaved.setNome("Some different Nome");
        Cliente clienteUpdated = this.clienteRepository.save(clienteSaved);
        Assertions.assertThat(clienteUpdated).isNotNull();
        Assertions.assertThat(clienteUpdated.getId()).isNotNull();
        Assertions.assertThat(clienteUpdated.getNome()).isEqualTo(clienteSaved.getNome());
    }

    @Test
    @DisplayName("Method: delete - removes Cliente when Successful")
    void delete_RemovesCliente_WhenSuccessful() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        this.clienteRepository.delete(clienteSaved);
        Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteSaved.getId());
        Assertions.assertThat(clienteOptional).isEmpty();
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
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setNome("");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome is null")
    void save_ThrowConstraintViolationException_WhenNomeIsNull() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setNome(null);
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when nome contains digits")
    void save_ThrowConstraintViolationException_WhenNomeContainsLetters() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setNome("Dri5no");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findByNomeIgnoreCaseContaining - returns list of Cliente when successful")
    void findByNomeIgnoreCaseContaining_ReturnsListOfCliente_WhenSuccessful() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String nome = clienteSaved.getNome();
        List<Cliente> clientes = this.clienteRepository.findByNomeIgnoreCaseContaining(nome);
        Assertions.assertThat(clientes)
                .isNotEmpty()
                .contains(clienteSaved);
    }
    @Test
    @DisplayName("Method: findByNomeIgnoreCaseContaining - returns empty list of Cliente searched by nome when any Cliente not founded")
    void findByNomeIgnoreCaseContaining_ReturnsEmptyListOfCliente_WhenClienteNotFound() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String nome = "SomeNastyName";
        List<Cliente> clientes = this.clienteRepository.findByNomeIgnoreCaseContaining(nome);
        Assertions.assertThat(clientes)
                .doesNotContain(clienteSaved)
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
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSobrenome("");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sobrenome is null")
    void save_ThrowConstraintViolationException_WhenSobrenomeIsNull() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSobrenome(null);
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sobrenome contains digits")
    void save_ThrowConstraintViolationException_WhenSobrenomeContainsLetters() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSobrenome("Pereir5");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findBySobrenomeIgnoreCaseContaining - returns list of Cliente  when successful")
    void findBySobrenomeIgnoreCaseContaining_ReturnsListOfCliente_WhenSuccessful() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String sobrenome = clienteSaved.getSobrenome();
        List<Cliente> clientes = this.clienteRepository.findBySobrenomeIgnoreCaseContaining(sobrenome);
        Assertions.assertThat(clientes)
                .isNotEmpty()
                .contains(clienteSaved);
    }
    @Test
    @DisplayName("Method: findBySobrenomeIgnoreCaseContaining - returns empty list of Cliente searched by sobrenome when any Cliente not founded")
    void findBySobrenomeIgnoreCaseContaining_ReturnsEmptyListOfCliente_WhenClienteNotFound() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String sobrenome = "SomeNastySobrenome";
        List<Cliente> clientes = this.clienteRepository.findByNomeIgnoreCaseContaining(sobrenome);
        Assertions.assertThat(clientes)
                .doesNotContain(clienteSaved)
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
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSexo(null);
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo is blank")
    void save_ThrowConstraintViolationException_WhenSexoIsBlank() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSexo("");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo have length greater than 1")
    void save_ThrowConstraintViolationException_WhenSexoLengthGreaterThan1() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSexo("ff");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo is digit")
    void save_ThrowConstraintViolationException_WhenSexoIsDigit() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSexo("1");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when sexo isn't any of: mfoMFO ")
    void save_ThrowConstraintViolationException_WhenSexoValueIsntValid() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setSexo("t");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("Method: findBySexoIgnoreCase - returns list of Cliente when successful")
    void findBySexo_ReturnsListOfCliente_WhenSuccessful() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String sexo = clienteSaved.getSexo();
        List<Cliente> clientes = this.clienteRepository.findBySexoIgnoreCase(sexo);
        Assertions.assertThat(clientes)
                .isNotEmpty()
                .contains(clienteSaved);
    }
    @Test
    @DisplayName("Method: findBySexoIgnoreCase - returns empty list of Cliente searched by sexo when any Cliente not founded")
    void findBySexoIgnoreCase_ReturnsEmptyListOfCliente_WhenClienteNotFound() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String sexo = "o";
        List<Cliente> clientes = this.clienteRepository.findBySexoIgnoreCase(sexo);
        Assertions.assertThat(clientes)
                .doesNotContain(clienteSaved)
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
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setCpf("");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf is null")
    void save_ThrowConstraintViolationException_WhenCpfIsNull() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setCpf(null);
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf contains letters")
    void save_ThrowConstraintViolationException_WhenCpfContainLetters() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setCpf("9993337772a");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf lenght are greater than 11")
    void save_ThrowConstraintViolationException_WhenCpfLengthGreaterThan11() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setCpf("99900099911000");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when cpf lenght are less than 11")
    void save_ThrowConstraintViolationException_WhenCpfLengthLessThan11() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        clienteToBeSaved.setCpf("9990");
        Assertions.assertThatThrownBy(() -> clienteRepository.save(clienteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }


    @Test
    @DisplayName("Method: findByCpfContaining - returns list of Cliente when successful")
    void findByCpfContaining_ReturnsListOfCliente_WhenSuccessful() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String cpf = clienteSaved.getCpf();
        List<Cliente> clientes = this.clienteRepository.findByCpfContaining(cpf);
        Assertions.assertThat(clientes)
                .isNotEmpty()
                .contains(clienteSaved);
    }

    @Test
    @DisplayName("Method: findByCpfContaining - returns empty list of Cliente searched by cpf when any Cliente not founded")
    void findByCpfContaining_ReturnsEmptyListOfCliente_WhenClienteNotFound() {
        Cliente clienteToBeSaved = ClienteCreator.createCliente();
        Cliente clienteSaved = this.clienteRepository.save(clienteToBeSaved);
        String cpf = "00000000000";
        List<Cliente> clientes = this.clienteRepository.findBySexoIgnoreCase(cpf);
        Assertions.assertThat(clientes)
                .doesNotContain(clienteSaved)
                .isEmpty();
    }
}
