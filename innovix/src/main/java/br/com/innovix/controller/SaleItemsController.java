package br.com.innovix.controller;

import br.com.innovix.dto.SaleItemsDTO;
import br.com.innovix.entity.SaleItemsEntity;
import br.com.innovix.exception.SaleItemNotFoundException;
import br.com.innovix.repository.SaleItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saleItems")
public class SaleItemsController {

    private final SaleItemsRepository saleItemsRepository;

    @Autowired
    public SaleItemsController(SaleItemsRepository saleItemsRepository) {
        this.saleItemsRepository = saleItemsRepository;
    }

    @PutMapping("/{id}")
    public SaleItemsDTO updateSaleItem(@PathVariable Long id, @RequestBody SaleItemsDTO saleItemsDTO) {
        SaleItemsEntity saleItem = saleItemsRepository.findById(id)
                .orElseThrow(() -> new SaleItemNotFoundException("Item de promoção não encontrado com ID: " + id));

        saleItem.setDescription(saleItemsDTO.description());
        saleItem.setPrice(saleItemsDTO.price());
        saleItem.setQuantity(saleItemsDTO.quantity());

        SaleItemsEntity updatedSaleItem = saleItemsRepository.save(saleItem);
        return SaleItemsDTO.fromEntity(updatedSaleItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSaleItem(@PathVariable Long id) {
        SaleItemsEntity saleItem = saleItemsRepository.findById(id)
                .orElseThrow(() -> new SaleItemNotFoundException("Item de promoção não encontrado com ID: " + id));

        saleItemsRepository.delete(saleItem);
        return ResponseEntity.ok().build();
    }
}
