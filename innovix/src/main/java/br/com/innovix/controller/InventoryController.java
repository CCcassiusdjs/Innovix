package br.com.innovix.controller;

import br.com.innovix.dto.InventoryDTO;
import br.com.innovix.entity.InventoryEntity;
import br.com.innovix.exception.InventoryException;
import br.com.innovix.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @PutMapping("/{id}")
    public InventoryDTO updateInventory(@PathVariable Long id, @RequestBody InventoryDTO inventoryDTO) {
        InventoryEntity inventoryEntity = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryException("Item de estoque não encontrado com ID: " + id));

        inventoryEntity.setQuantity(inventoryDTO.quantity());
        inventoryEntity.setLastUpdate(inventoryDTO.lastUpdate());
        inventoryEntity.setLocation(inventoryDTO.location());
        // Atualizar referência do produto, se necessário

        InventoryEntity updatedInventory = inventoryRepository.save(inventoryEntity);
        return InventoryDTO.fromEntity(updatedInventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        InventoryEntity inventoryEntity = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryException("Item de estoque não encontrado com ID: " + id));

        inventoryRepository.delete(inventoryEntity);
        return ResponseEntity.ok().build();
    }
}
