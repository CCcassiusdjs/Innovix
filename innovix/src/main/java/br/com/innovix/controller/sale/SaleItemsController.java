package br.com.innovix.controller.sale;

import br.com.innovix.dto.sale.SaleItemsDTO;
import br.com.innovix.service.sale.SaleItemsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saleItems")
@Validated
public class SaleItemsController {

    private final SaleItemsService saleItemsService;

    @Autowired
    public SaleItemsController(SaleItemsService saleItemsService) {
        this.saleItemsService = saleItemsService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleItemsDTO> updateSaleItem(@PathVariable Long id, @Valid @RequestBody SaleItemsDTO saleItemsDTO) {
        SaleItemsDTO updatedSaleItem = saleItemsService.updateSaleItem(id, saleItemsDTO);
        return ResponseEntity.ok(updatedSaleItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable Long id) {
        saleItemsService.deleteSaleItem(id);
        return ResponseEntity.noContent().build();
    }
}