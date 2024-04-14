package br.com.innovix.service;

import br.com.innovix.dto.SalesDTOs;
import br.com.innovix.entity.SalesEntities;
import br.com.innovix.exception.Exceptions;
import br.com.innovix.repository.SalesRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

public class SalesServices {

    @Service
    public static class SaleService {

        @Autowired
        private SalesRepositories.SaleRepository saleRepository;

        public SalesDTOs.SaleDTO updateSale(Long id, SalesDTOs.SaleDTO saleDTO) {
            SalesEntities.SaleEntity saleEntity = saleRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.PromotionException("Promoção não encontrada com o ID: " + id));

            saleEntity.setDate(Date.valueOf(saleDTO.date()));
            saleEntity.setStartDate(Date.valueOf(saleDTO.start_date()));
            saleEntity.setEndDate(Date.valueOf(saleDTO.end_date()));
            saleEntity.setType(saleDTO.type());

            return SalesDTOs.SaleDTO.fromEntity(saleRepository.save(saleEntity));
        }

        public void deleteSale(Long id) {
            SalesEntities.SaleEntity saleEntity = saleRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.PromotionException("Promoção não encontrada com o ID: " + id));
            saleRepository.delete(saleEntity);
        }
    }

    @Service
    public static class SaleItemsService {

        @Autowired
        private SalesRepositories.SaleItemsRepository saleItemsRepository;

        public SalesDTOs.SaleItemsDTO updateSaleItem(Long id, SalesDTOs.SaleItemsDTO saleItemsDTO) {
            SalesEntities.SaleItemsEntity saleItem = saleItemsRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.SaleItemNotFoundException("Item de promoção não encontrado com ID: " + id));

            saleItem.setDescription(saleItemsDTO.description());
            saleItem.setPrice(saleItemsDTO.price());
            saleItem.setQuantity(saleItemsDTO.quantity());

            return SalesDTOs.SaleItemsDTO.fromEntity(saleItemsRepository.save(saleItem));
        }

        public void deleteSaleItem(Long id) {
            SalesEntities.SaleItemsEntity saleItem = saleItemsRepository.findById(id)
                    .orElseThrow(() -> new Exceptions.SaleItemNotFoundException("Item de promoção não encontrado com ID: " + id));
            saleItemsRepository.delete(saleItem);
        }
    }
}
