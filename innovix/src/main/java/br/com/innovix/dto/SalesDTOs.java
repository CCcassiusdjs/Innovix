package br.com.innovix.dto;

import br.com.innovix.entity.ProductEntities;
import br.com.innovix.entity.SalesEntities;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SalesDTOs {

    public record SaleDTO(
            Long codSale,
            LocalDate date,
            String type,
            LocalDate start_date,
            LocalDate end_date
    ) {
        public static SaleDTO fromEntity(SalesEntities.SaleEntity entity) {
            return new SaleDTO(
                    entity.getCodSale(),
                    entity.getDate().toLocalDate(),
                    entity.getType(),
                    entity.getStartDate().toLocalDate(),
                    entity.getEndDate().toLocalDate()
            );
        }

        public SalesEntities.SaleEntity toEntity() {
            SalesEntities.SaleEntity entity = new SalesEntities.SaleEntity();
            entity.setCodSale(this.codSale);
            entity.setDate(Date.valueOf(this.date));
            entity.setType(this.type);
            entity.setStartDate(Date.valueOf(this.start_date));
            entity.setEndDate(Date.valueOf(this.end_date));
            return entity;
        }

        public long getDurationInDays() {
            if (start_date == null || end_date == null) {
                throw new IllegalStateException("Start and end dates are required to calculate the duration.");
            }
            return ChronoUnit.DAYS.between(start_date, end_date);
        }
    }

    public record SaleItemsDTO(
            Long codSaleItem,
            String description,
            Double price,
            Integer quantity,
            Long codSale,
            Long codProd
    ) {
        public static SaleItemsDTO fromEntity(SalesEntities.SaleItemsEntity entity) {
            return new SaleItemsDTO(
                    entity.getCodSaleItem(),
                    entity.getDescription(),
                    entity.getPrice(),
                    entity.getQuantity(),
                    entity.getSaleByCodSale().getCodSale(),
                    entity.getProductByCodProd().getCodProd()
            );
        }

        public SalesEntities.SaleItemsEntity toEntity() {
            SalesEntities.SaleItemsEntity entity = new SalesEntities.SaleItemsEntity();
            entity.setCodSaleItem(this.codSaleItem);
            entity.setDescription(this.description);
            entity.setPrice(this.price);
            entity.setQuantity(this.quantity);

            SalesEntities.SaleEntity saleEntity = new SalesEntities.SaleEntity(); // Placeholder: must be fetched or handled appropriately
            saleEntity.setCodSale(this.codSale);
            entity.setSaleByCodSale(saleEntity);

            ProductEntities.ProductEntity productEntity = new ProductEntities.ProductEntity(); // Placeholder: must be fetched or handled appropriately
            productEntity.setCodProd(this.codProd);
            entity.setProductByCodProd(productEntity);

            return entity;
        }
    }
}
