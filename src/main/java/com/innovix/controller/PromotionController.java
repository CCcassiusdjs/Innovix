package com.innovix.controller;

import com.innovix.dto.PromotionDTO;
import com.innovix.mapper.PromotionMapper;
import com.innovix.usecase.EmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public PromotionController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listAll() {
        return employeeUseCase.listAllPromotions().stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public PromotionDTO save(@RequestBody PromotionDTO promotionDTO) {
        return PromotionMapper.INSTANCE.toDto(
                employeeUseCase.createPromotion(PromotionMapper.INSTANCE.toEntity(promotionDTO))
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public PromotionDTO getById(@PathVariable Long id) {
        return PromotionMapper.INSTANCE.toDto(employeeUseCase.getPromotionById(id));
    }

    @GetMapping("/season/{season}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listBySeason(@PathVariable String season) {
        return employeeUseCase.listPromotionsBySeason(season).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/init-date")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listByInitLocalDateBefore(@RequestParam LocalDate date) {
        return employeeUseCase.listPromotionsByInitLocalDateBefore(date).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/end-date")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listByEndLocalDateAfter(@RequestParam LocalDate date) {
        return employeeUseCase.listPromotionsByEndLocalDateAfter(date).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listByEmployeeId(@PathVariable Long employeeId) {
        return employeeUseCase.listPromotionsByEmployeeId(employeeId).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deletePromotion(id);
    }
}
