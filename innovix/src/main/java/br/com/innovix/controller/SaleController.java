package br.com.innovix.controller;

import br.com.innovix.dto.SaleDTO;
import br.com.innovix.entity.SaleEntity;
import br.com.innovix.exception.PromotionException;
import br.com.innovix.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @PutMapping("/{id}")
    public SaleDTO updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO) {
        SaleEntity saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new PromotionException("Promoção não encontrada com o ID: " + id));

        saleEntity.setDate(Date.valueOf(saleDTO.date()));
        saleEntity.setStartDate(Date.valueOf(saleDTO.start_date()));
        saleEntity.setEndDate(Date.valueOf(saleDTO.end_date()));
        saleEntity.setType(saleDTO.type());

        SaleEntity updatedSale = saleRepository.save(saleEntity);
        return SaleDTO.fromEntity(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        SaleEntity saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new PromotionException("Promoção não encontrada com o ID: " + id));

        saleRepository.delete(saleEntity);
        return ResponseEntity.ok().build();
    }
}
