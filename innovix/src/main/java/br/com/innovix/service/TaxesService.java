package br.com.innovix.service;

import br.com.innovix.dto.TaxesDTO;
import br.com.innovix.entity.TaxesEntity;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.repository.TaxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxesService {

    @Autowired
    private TaxesRepository taxesRepository;

    public TaxesDTO updateTax(Long id, TaxesDTO taxesDTO) {
        TaxesEntity taxesEntity = taxesRepository.findById(id)
                .orElseThrow(() -> new Exceptions.TaxException("Imposto não encontrado com ID: " + id));

        taxesEntity.setState(taxesDTO.state());
        taxesEntity.setValue(taxesDTO.value());
        taxesEntity.setDescription(taxesDTO.description());
        // Atualizar a relação com OrderEntity, se necessário

        return TaxesDTO.fromEntity(taxesRepository.save(taxesEntity));
    }

    public void deleteTax(Long id) {
        TaxesEntity tax = taxesRepository.findById(id)
                .orElseThrow(() -> new Exceptions.TaxException("Imposto não encontrado com ID: " + id));
        taxesRepository.delete(tax);
    }
}
