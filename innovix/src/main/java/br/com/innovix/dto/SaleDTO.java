package br.com.innovix.dto;

import br.com.innovix.entity.SaleEntity;
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
                (long) entity.getCodSale(),
                entity.getDate().toLocalDate(),
                entity.getType(),
                entity.getStartDate().toLocalDate(),
                entity.getEndDate().toLocalDate()
        );
    }

    public SaleEntity toEntity() {
        SaleEntity entity = new SaleEntity();
        entity.setCodSale(Math.toIntExact(this.codSale));
        entity.setDate(Date.valueOf(this.date));
        entity.setType(this.type);
        entity.setStartDate(Date.valueOf(this.start_date));
        entity.setEndDate(Date.valueOf(this.end_date));
        return entity;
    }

    public long getDurationInDays() {
        if (start_date == null || end_date == null) {
            throw new IllegalStateException("Datas de início e fim são necessárias para calcular a duração.");
        }
        return ChronoUnit.DAYS.between(start_date, end_date);
    }

    // Métodos de validação são removidos devido à imutabilidade do record
}
