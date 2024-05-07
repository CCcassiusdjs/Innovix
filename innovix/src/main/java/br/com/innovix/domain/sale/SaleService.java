package br.com.innovix.domain.sale;

import br.com.innovix.exception.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private PromotionService promotionService; // Integrar PromotionService

    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        SaleEntity saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new Exceptions.PromotionException("Promoção não encontrada com o ID: " + id));

        saleEntity.setDate(Date.valueOf(saleDTO.date()));
        saleEntity.setStartDate(Date.valueOf(saleDTO.start_date()));
        saleEntity.setEndDate(Date.valueOf(saleDTO.end_date()));
        saleEntity.setType(saleDTO.type());

        // Aplica promoções antes de salvar
        applyPromotions((List<SaleItemsEntity>) saleEntity.getSaleItems());

        return SaleDTO.fromEntity(saleRepository.save(saleEntity));
    }

    private void applyPromotions(List<SaleItemsEntity> saleItems) {
        promotionService.applyPromotions(saleItems);
    }

    public void deleteSale(Long id) {
        SaleEntity saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new Exceptions.PromotionException("Promoção não encontrada com o ID: " + id));
        saleRepository.delete(saleEntity);
    }


}
