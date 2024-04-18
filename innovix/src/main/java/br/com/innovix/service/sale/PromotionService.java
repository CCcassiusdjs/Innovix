package br.com.innovix.service.sale;

import br.com.innovix.entity.product.ProductEntity;
import br.com.innovix.entity.sale.SaleItemsEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService {

    // Aplica promoções aos itens de venda conforme as regras de negócio
    public void applyPromotions(List<SaleItemsEntity> saleItems) {
        for (SaleItemsEntity item : saleItems) {
            applyIndividualPromotion(item);
        }
    }

    private void applyIndividualPromotion(SaleItemsEntity item) {
        ProductEntity product = item.getProduct();
        if (product.isPromotionActive() && LocalDate.now().isBefore(product.getPromotionEndDate())) {
            switch (product.getPromotionType()) {
                case "Leve3Pague2":
                    applyLeve3Pague2(item);
                    break;
                case "Pack":
                    applyPackPromotion(item, product.getMinimumQuantityForPromotion(), product.getPromotionPrice());
                    break;
                default:
                    // Log or handle the case where promotion type is unknown
                    break;
            }
        }
    }

    // Aplica a promoção "Leve 3 Pague 2" ao item
    private void applyLeve3Pague2(SaleItemsEntity item) {
        int quantity = item.getQuantity();
        if (quantity >= 3) {
            int freeItems = quantity / 3;
            double discount = item.getPrice() * freeItems;
            item.setPrice(discount);
        }
    }

    // Aplica a promoção "Pack" ao item, se a quantidade do item for superior ao mínimo necessário
    private void applyPackPromotion(SaleItemsEntity item, int minimumQuantity, double promotionPrice) {
        if (item.getQuantity() >= minimumQuantity) {
            item.setPrice(promotionPrice);
        }
    }
}
