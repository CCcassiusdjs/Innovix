package br.com.innovix.controller;

import br.com.innovix.dto.TaxesDTO;
import br.com.innovix.entity.TaxesEntity;
import br.com.innovix.exception.TaxException;
import br.com.innovix.repository.TaxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taxes")
public class TaxesController {

    private final TaxesRepository taxesRepository;

    @Autowired
    public TaxesController(TaxesRepository taxesRepository) {
        this.taxesRepository = taxesRepository;
    }

    @PutMapping("/{id}")
    public TaxesDTO updateTax(@PathVariable Long id, @RequestBody TaxesDTO taxesDTO) {
        TaxesEntity taxesEntity = taxesRepository.findById(id)
                .orElseThrow(() -> new TaxException("Imposto não encontrado com ID: " + id));

        taxesEntity.setState(taxesDTO.state());
        taxesEntity.setValue(taxesDTO.value());
        taxesEntity.setDescription(taxesDTO.description());
        // Atualizar a relação com OrderEntity, se necessário

        TaxesEntity updatedTax = taxesRepository.save(taxesEntity);
        return TaxesDTO.fromEntity(updatedTax);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTax(@PathVariable Long id) {
        TaxesEntity tax = taxesRepository.findById(id)
                .orElseThrow(() -> new TaxException("Imposto não encontrado com ID: " + id));

        taxesRepository.delete(tax);
        return ResponseEntity.ok().build();
    }
}
