package com.innovix.repository;

import com.innovix.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findBySeason(String season);
    List<Promotion> findByInitLocalDateBefore(LocalDate date);
    List<Promotion> findByEndLocalDateAfter(LocalDate date);
    List<Promotion> findByEmployeeId(Long employeeId);
}
