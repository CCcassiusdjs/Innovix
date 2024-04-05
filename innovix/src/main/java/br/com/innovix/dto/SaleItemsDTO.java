package br.com.innovix.dto;

import br.com.innovix.entity.SaleItemsEntity;
import br.com.innovix.entity.ProductEntity;
import br.com.innovix.entity.SaleEntity;

public record SaleItemsDTO(
        Long codSaleItem,
        String description,
        Double price,
        Integer quantity,
        Long codSale,
        Long codProd
) {
    public static SaleItemsDTO fromEntity(SaleItemsEntity entity) {
        return new SaleItemsDTO(
                (long) entity.getCodSaleItem(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQuantity(),
                (long) entity.getSaleByCodSale().getCodSale(),
                (long) entity.getProductByCodProd().getCodProd()
        );
    }

    public SaleItemsEntity toEntity() {
        SaleItemsEntity entity = new SaleItemsEntity();
        entity.setCodSaleItem(Math.toIntExact(this.codSaleItem));
        entity.setDescription(this.description);
        entity.setPrice(this.price);
        entity.setQuantity(this.quantity);
        entity.setSaleByCodSale(new SaleEntity()); // Ajuste necessário aqui
        entity.setProductByCodProd(new ProductEntity()); // Ajuste necessário aqui
        return entity;
    }

    // Métodos de validação são removidos devido à imutabilidade do record
}
