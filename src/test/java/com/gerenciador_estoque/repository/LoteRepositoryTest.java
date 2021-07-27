package com.gerenciador_estoque.repository;

import com.gerenciador_estoque.model.Lote;
import com.gerenciador_estoque.util.LoteCreator;
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
@DisplayName("Tests for LoteRepository class")
public class LoteRepositoryTest {
    @Autowired
    private LoteRepository loteRepository;

    @Test
    @DisplayName("Method: save - persists Lote when Successful")
    void save_PersistsLote_WhenSuccessful() {
        Lote loteToBeSaved = LoteCreator.createLote();
        Lote loteSaved = this.loteRepository.save(loteToBeSaved);
        Assertions.assertThat(loteSaved).isNotNull();
        Assertions.assertThat(loteSaved.getId()).isNotNull();
        Assertions.assertThat(loteSaved.getDataFabricacao()).isEqualTo(loteToBeSaved.getDataFabricacao());
    }

    @Test
    @DisplayName("Method save - updates Lote when Successful")
    void save_UpdatesLote_WhenSuccessful() {
        Lote loteToBeSaved = LoteCreator.createLote();
        Lote loteSaved = this.loteRepository.save(loteToBeSaved);
        loteSaved.setDataFabricacao(LocalDate.of(1900, 10, 20));
        Lote loteUpdated = this.loteRepository.save(loteSaved);
        Assertions.assertThat(loteUpdated).isNotNull();
        Assertions.assertThat(loteUpdated.getId()).isNotNull();
        Assertions.assertThat(loteUpdated.getDataFabricacao()).isEqualTo(loteSaved.getDataFabricacao());
    }

    @Test
    @DisplayName("Method: delete - removes Lote when SUccessful")
    void delete_RemovesLote_WhenSuccessful() {
        Lote loteToBeSaved = LoteCreator.createLote();
        Lote loteSaved = this.loteRepository.save(loteToBeSaved);
        this.loteRepository.delete(loteSaved);
        Optional<Lote> loteOptional = this.loteRepository.findById(loteSaved.getId());
        Assertions.assertThat(loteOptional).isEmpty();
    }

    /**
     * Tests for dataFabricacao attribute
     * Cannot be Null
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when dataFabricacao is null")
    void save_ThrowConstraintViolationException_WhenDataFabricacaoIsNull() {
        Lote loteToBeSaved = LoteCreator.createLote();
        loteToBeSaved.setDataFabricacao(null);
        Assertions.assertThatThrownBy(() -> this.loteRepository.save(loteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }


    /**
     * Tests for dataVencimento attribute
     * Cannot be Null
     * findByDataVencimentoBetween(LocalDate start, LocalDate end)
     */
    @Test
    @DisplayName("Method: save - throw ConstraintViolationException when dataVencimento is null")
    void save_ThrowConstraintViolationException_WhenDataVencimentoIsNull() {
        Lote loteToBeSaved = LoteCreator.createLote();
        loteToBeSaved.setDataVencimento(null);
        Assertions.assertThatThrownBy(() -> this.loteRepository.save(loteToBeSaved))
                .isInstanceOf(ConstraintViolationException.class);
    }
    @Test
    @DisplayName("Method: findByDataVencimentoBetween - return List of Lote when successful")
    void findByDataVencimentoBetween_ReturnsListOfLote_WhenSuccessful() {
        Lote loteToBeSaved = new Lote(0L,
                LocalDate.of(2000, 10, 20),
                LocalDate.of(2019, 10, 20));
        Lote loteSaved = this.loteRepository.save(loteToBeSaved);
        LocalDate localDateStart = LocalDate.of(2000, 10, 1);
        List<Lote> lotes = this.loteRepository.findByDataVencimentoBetween(localDateStart, LocalDate.now());
        Assertions.assertThat(lotes)
                .isNotEmpty()
                .contains(loteSaved);
    }
}
