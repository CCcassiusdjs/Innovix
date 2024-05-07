package br.com.innovix.domain.sale;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record SaleDTO(
        Long codSale,
        LocalDate date,
        String type,
        LocalDate start_date,
        LocalDate end_date
) {
    public static SaleDTO fromEntity(SaleEntity entity) {
        return new SaleDTO(
                entity.getCodSale(),
                entity.getDate() != null ? entity.getDate().toLocalDate() : null,
                entity.getType(),
                entity.getStartDate() != null ? entity.getStartDate().toLocalDate() : null,
                entity.getEndDate() != null ? entity.getEndDate().toLocalDate() : null
        );
    }

    public SaleEntity toEntity() {
        SaleEntity entity = new SaleEntity();
        entity.setCodSale(this.codSale);
        entity.setDate(this.date != null ? Date.valueOf(this.date) : null);
        entity.setType(this.type);
        entity.setStartDate(this.start_date != null ? Date.valueOf(this.start_date) : null);
        entity.setEndDate(this.end_date != null ? Date.valueOf(this.end_date) : null);
        return entity;
    }

    public long getDurationInDays() {
        if (start_date == null || end_date == null) {
            throw new IllegalStateException("Start and end dates are required to calculate the duration.");
        }
        return ChronoUnit.DAYS.between(start_date, end_date);
    }
}