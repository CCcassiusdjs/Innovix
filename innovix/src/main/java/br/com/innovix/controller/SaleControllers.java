package br.com.innovix.controller;

import br.com.innovix.dto.SalesDTOs;
import br.com.innovix.service.SalesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
public class SaleControllers {

    @RestController
    @RequestMapping("/sales")
    public static class SaleController {

        private final SalesServices.SaleService saleService;

        @Autowired
        public SaleController(SalesServices.SaleService saleService) {
            this.saleService = saleService;
        }

        
        @PutMapping("/{id}")
        public SalesDTOs.SaleDTO updateSale(@PathVariable Long id, @RequestBody SalesDTOs.SaleDTO saleDTO) {
            return saleService.updateSale(id, saleDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteSale(@PathVariable Long id) {
            saleService.deleteSale(id);
            return ResponseEntity.ok().build();
        }
    }

    @RestController
    @RequestMapping("/saleItems")
    public static class SaleItemsController {

        private final SalesServices.SaleItemsService saleItemsService;

        @Autowired
        public SaleItemsController(SalesServices.SaleItemsService saleItemsService) {
            this.saleItemsService = saleItemsService;
        }

        
        @PutMapping("/{id}")
        public SalesDTOs.SaleItemsDTO updateSaleItem(@PathVariable Long id, @RequestBody SalesDTOs.SaleItemsDTO saleItemsDTO) {
            return saleItemsService.updateSaleItem(id, saleItemsDTO);
        }

        
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteSaleItem(@PathVariable Long id) {
            saleItemsService.deleteSaleItem(id);
            return ResponseEntity.ok().build();
        }
    }
}
