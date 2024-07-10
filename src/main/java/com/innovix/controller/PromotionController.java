package com.innovix.controller;

import com.innovix.dto.PromotionDTO;
import com.innovix.mapper.PromotionMapper;
import com.innovix.usecase.EmployeeUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing promotions.
 */
@RestController
@RequestMapping("/api/promotions")
public class PromotionController {

    private final EmployeeUseCase employeeUseCase;

    @Autowired
    public PromotionController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    /**
     * Lists all promotions.
     *
     * @return A list of all promotions.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listAll() {
        return employeeUseCase.listAllPromotions().stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new promotion.
     *
     * @param
     * @param promotionDTO The promotion data transfer object.
     * @return The saved promotion data transfer object.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public PromotionDTO save(@Valid @RequestBody PromotionDTO promotionDTO) {
        return PromotionMapper.INSTANCE.toDto(
                employeeUseCase.createPromotion(PromotionMapper.INSTANCE.toEntity(promotionDTO))
        );
    }

    /**
     * Gets a promotion by ID.
     *
     * @param id The ID of the promotion.
     * @return The promotion data transfer object.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public PromotionDTO getById(@PathVariable Long id) {
        return PromotionMapper.INSTANCE.toDto(employeeUseCase.getPromotionById(id));
    }

    /**
     * Lists promotions by season.
     *
     * @param season The season of the promotion.
     * @return A list of promotions for the specified season.
     */
    @GetMapping("/season/{season}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listBySeason(@PathVariable String season) {
        return employeeUseCase.listPromotionsBySeason(season).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists promotions that start before a specified date.
     *
     * @param date The date to compare against the start date of the promotions.
     * @return A list of promotions that start before the specified date.
     */
    @GetMapping("/init-date")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listByInitLocalDateBefore(@RequestParam LocalDate date) {
        return employeeUseCase.listPromotionsByInitLocalDateBefore(date).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists promotions that end after a specified date.
     *
     * @param date The date to compare against the end date of the promotions.
     * @return A list of promotions that end after the specified date.
     */
    @GetMapping("/end-date")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listByEndLocalDateAfter(@RequestParam LocalDate date) {
        return employeeUseCase.listPromotionsByEndLocalDateAfter(date).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Lists promotions by employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return A list of promotions associated with the specified employee.
     */
    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'EMPLOYEE')")
    public List<PromotionDTO> listByEmployeeId(@PathVariable Long employeeId) {
        return employeeUseCase.listPromotionsByEmployeeId(employeeId).stream()
                .map(PromotionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a promotion by ID.
     *
     * @param id The ID of the promotion to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public void delete(@PathVariable Long id) {
        employeeUseCase.deletePromotion(id);
    }
}
