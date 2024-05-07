package br.com.innovix.controller.product;

import br.com.innovix.domain.product.inventory.InventoryDTO;
import br.com.innovix.domain.product.product.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final ProductServices.InventoryService inventoryService;

    @Autowired
    public InventoryController(ProductServices.InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @PutMapping("/{id}")
    public InventoryDTO updateInventory(@PathVariable Long id, @RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(id, inventoryDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }
}
