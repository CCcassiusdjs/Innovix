package com.innovix.repository;

import com.innovix.entity.Person;
import com.innovix.entity.Promotion;
import com.innovix.repository.PersonRepository;
import com.innovix.repository.PromotionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PromotionRepositoryTest {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PersonRepository personRepository;

    private Person employee;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        employee = new Person();
        employee.setName("Jane Smith");
        employee = personRepository.save(employee);
    }

    @org.junit.jupiter.api.Test
    void tearDown() {
        promotionRepository.deleteAll();
        personRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    void findBySeason() {
        Promotion promotion = createSamplePromotion();
        promotion.setSeason("Summer");
        promotionRepository.save(promotion);

        List<Promotion> foundPromotions = promotionRepository.findBySeason("Summer");
        assertNotNull(foundPromotions);
        assertEquals(1, foundPromotions.size());
        assertEquals("Summer", foundPromotions.get(0).getSeason());
    }

    @org.junit.jupiter.api.Test
    void findByInitLocalDateBefore() {
        Promotion promotion = createSamplePromotion();
        promotion.setInitLocalDate(LocalDate.now().minusDays(5));
        promotionRepository.save(promotion);

        List<Promotion> foundPromotions = promotionRepository.findByInitLocalDateBefore(LocalDate.now());
        assertNotNull(foundPromotions);
        assertEquals(1, foundPromotions.size());
        assertEquals(promotion.getId(), foundPromotions.get(0).getId());
    }

    @org.junit.jupiter.api.Test
    void findByEndLocalDateAfter() {
        Promotion promotion = createSamplePromotion();
        promotion.setEndLocalDate(LocalDate.now().plusDays(5));
        promotionRepository.save(promotion);

        List<Promotion> foundPromotions = promotionRepository.findByEndLocalDateAfter(LocalDate.now());
        assertNotNull(foundPromotions);
        assertEquals(1, foundPromotions.size());
        assertEquals(promotion.getId(), foundPromotions.get(0).getId());
    }

    @org.junit.jupiter.api.Test
    void findByEmployeeId() {
        Promotion promotion = createSamplePromotion();
        promotion.setEmployee(employee);
        promotionRepository.save(promotion);

        List<Promotion> foundPromotions = promotionRepository.findByEmployeeId(employee.getId());
        assertNotNull(foundPromotions);
        assertEquals(1, foundPromotions.size());
        assertEquals(employee.getId(), foundPromotions.get(0).getEmployee().getId());
    }

    private Promotion createSamplePromotion() {
        Promotion promotion = new Promotion();
        promotion.setDescription("Sample Promotion");
        promotion.setSeason("Spring");
        promotion.setInitLocalDate(LocalDate.now());
        promotion.setEndLocalDate(LocalDate.now().plusDays(7));
        promotion.setPercentage(10.0);
        promotion.setRequiredQuantity(2);
        promotion.setFreeQuantity(1);
        promotion.setEmployee(new Person());
        return promotion;
    }
}
