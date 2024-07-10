
package com.innovix.repository;

import com.innovix.service.IcmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IcmsRepository {

    private final IcmsService icmsService;

    @Autowired
    public IcmsRepository(IcmsService icmsService) {
        this.icmsService = icmsService;
    }

    public double getIcmsValue(double subtotal, String state) {
        return icmsService.calculateIcms(subtotal, state);
    }
}