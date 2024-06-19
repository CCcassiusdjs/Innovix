package com.innovix.controller;

import com.innovix.dto.PromotionDTO;
import com.innovix.mapper.PromotionMapper;
import com.innovix.usecase.PromotionUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionUseCase promotionUseCase;

    @Autowired
    public PromotionController(PromotionUseCase promotionUseCase) {
        this.promotionUseCase = promotionUseCase;
    }

    @GetMapping
    public List<PromotionDTO> listAll() {
        return promotionUseCase.listAllPromotions().stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public PromotionDTO save(@RequestBody PromotionDTO promotionDTO) {
        return PromotionMapper.INSTANCE.toDto(
                promotionUseCase.createPromotion(PromotionMapper.INSTANCE.toEntity(promotionDTO))
        );
    }

    @GetMapping("/{id}")
    public PromotionDTO getById(@PathVariable Long id) {
        return PromotionMapper.INSTANCE.toDto(promotionUseCase.getPromotionById(id));
    }

    @GetMapping("/season/{season}")
    public List<PromotionDTO> listBySeason(@PathVariable String season) {
        return promotionUseCase.listPromotionsBySeason(season).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/init-date")
    public List<PromotionDTO> listByInitLocalDateBefore(@RequestParam LocalDate date) {
        return promotionUseCase.listPromotionsByInitLocalDateBefore(date).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/end-date")
    public List<PromotionDTO> listByEndLocalDateAfter(@RequestParam LocalDate date) {
        return promotionUseCase.listPromotionsByEndLocalDateAfter(date).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<PromotionDTO> listByEmployeeId(@PathVariable Long employeeId) {
        return promotionUseCase.listPromotionsByEmployeeId(employeeId).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        promotionUseCase.deletePromotion(id);
    }
}
