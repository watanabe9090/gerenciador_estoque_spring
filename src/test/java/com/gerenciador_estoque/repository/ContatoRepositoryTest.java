package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.util.Contato;
import com.gerenciador_estoque.util.ContatoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for ContatoRepository class")
public class ContatoRepositoryTest {
    @Autowired
    private ContatoRepository contatoRepository;

    @Test
    @DisplayName("Method: save - Persists Contato when Successful")
    void save_PersistsContato_WhenSuccessful() {
        Contato contatoToBeSaved = ContatoCreator.createContato();
        Contato contatoSaved = this.contatoRepository.save(contatoToBeSaved);
        Assertions.assertThat(contatoSaved).isNotNull();
        Assertions.assertThat(contatoSaved.getId()).isNotNull();
        Assertions.assertThat(contatoSaved.getEmail()).isEqualTo(contatoToBeSaved.getEmail());
    }

    @Test
    @DisplayName("Method: save - updates Contato when Successful")
    void save_UpdatesContato_WhenSuccessful() {
        Contato contatoToBeSaved = ContatoCreator.createContato();
        Contato contatoSaved = this.contatoRepository.save(contatoToBeSaved);
        contatoSaved.setEmail("somedifferentemail@hotmailc.om");
        Contato contatoUpdated = this.contatoRepository.save(contatoSaved);
        Assertions.assertThat(contatoUpdated).isNotNull();
        Assertions.assertThat(contatoUpdated.getId()).isEqualTo(contatoSaved.getId());
        Assertions.assertThat(contatoUpdated.getEmail()).isEqualTo(contatoSaved.getEmail());
    }

    @Test
    @DisplayName("Method: delete - removes Contato when Successful")
    void save_RemovesContato_WhenSuccessful() {
        Contato contatoToBeSaved = ContatoCreator.createContato();
        Contato contatoSaved = this.contatoRepository.save(contatoToBeSaved);
        this.contatoRepository.delete(contatoSaved);
        Optional<Contato> contatoOptional = this.contatoRepository.findById(contatoSaved.getId());
        Assertions.assertThat(contatoOptional).isEmpty();
    }

    /**
     * Tests for 'email' attribute
     * Cannot be null
     * Email
     */
}
