package br.com.innovix.service.sale;

import br.com.innovix.dto.sale.SaleItemsDTO;
import br.com.innovix.entity.sale.SaleItemsEntity;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.repository.sale.SaleItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SaleItemsService {

    private final SaleItemsRepository saleItemsRepository;

    @Autowired
    public SaleItemsService(SaleItemsRepository saleItemsRepository) {
        this.saleItemsRepository = saleItemsRepository;
    }

    @Transactional
    public SaleItemsDTO updateSaleItem(Long id, SaleItemsDTO saleItemsDTO) {
        SaleItemsEntity saleItem = saleItemsRepository.findById(id)
                .orElseThrow(() -> new Exceptions.SaleItemNotFoundException("Item de promoção não encontrado com ID: " + id));

        saleItem.setDescription(saleItemsDTO.description());
        saleItem.setPrice(saleItemsDTO.price());
        saleItem.setQuantity(saleItemsDTO.quantity());

        return SaleItemsDTO.fromEntity(saleItemsRepository.save(saleItem));
    }

    @Transactional
    public void deleteSaleItem(Long id) {
        saleItemsRepository.findById(id)
                .orElseThrow(() -> new Exceptions.SaleItemNotFoundException("Item de promoção não encontrado com ID: " + id));
        saleItemsRepository.deleteById(id);
    }
}