package br.com.innovix.domain.sale;

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
                entity.getCodSaleItem(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getQuantity(),
                entity.getSale().getCodSale(),
                entity.getCodSaleItem()
        );
    }

    public SaleItemsEntity toEntity() {
        SaleItemsEntity entity = new SaleItemsEntity();
        entity.setCodSaleItem(this.codSaleItem);
        entity.setDescription(this.description);
        entity.setPrice(this.price);
        entity.setQuantity(this.quantity);

        // Presumimos que as instâncias apropriadas de SaleEntity e ProductEntity são gerenciadas externamente
        // Talvez injetadas ou buscadas via repositório, conforme o contexto de uso
        return entity;
    }
}