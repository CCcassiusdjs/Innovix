package br.com.innovix.controller.sale;


import br.com.innovix.domain.sale.SaleDTO;
import br.com.innovix.domain.sale.SaleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
@Validated
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable @NotBlank Long id, @Valid @RequestBody SaleDTO saleDTO) {
        SaleDTO updatedSale = saleService.updateSale(id, saleDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable @NotBlank Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}