package br.com.innovix.controller.sale;

import br.com.innovix.domain.sale.SaleItemsDTO;
import br.com.innovix.domain.sale.SaleItemsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saleItems")
@Validated
public class SaleItemsController {

    @Autowired
    private  SaleItemsService saleItemsService;

    @PutMapping("/{id}")
    public ResponseEntity<SaleItemsDTO> updateSaleItem(@PathVariable @NotBlank Long id, @Valid @RequestBody SaleItemsDTO saleItemsDTO) {
        SaleItemsDTO updatedSaleItem = saleItemsService.updateSaleItem(id, saleItemsDTO);
        return ResponseEntity.ok(updatedSaleItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable @NotBlank Long id) {
        saleItemsService.deleteSaleItem(id);
        return ResponseEntity.noContent().build();
    }
}