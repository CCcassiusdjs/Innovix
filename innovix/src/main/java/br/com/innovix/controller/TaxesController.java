package br.com.innovix.controller;

import br.com.innovix.dto.TaxesDTO;
import br.com.innovix.service.TaxesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/taxes")
public class TaxesController {

    private final TaxesService taxesService;

    @Autowired
    public TaxesController(TaxesService taxesService) {
        this.taxesService = taxesService;
    }

    @PutMapping("/{id}")
    public TaxesDTO updateTax(@PathVariable Long id, @RequestBody TaxesDTO taxesDTO) {
        return taxesService.updateTax(id, taxesDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTax(@PathVariable Long id) {
        taxesService.deleteTax(id);
        return ResponseEntity.ok().build();
    }
}
