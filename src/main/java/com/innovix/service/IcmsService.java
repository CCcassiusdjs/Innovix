package com.innovix.service;

import com.innovix.config.IcmsRates;
import org.springframework.stereotype.Service;

@Service
public class IcmsService {

    public double calculateIcms(double subtotal, String state) {
        double rate = IcmsRates.getRate(state);
        double value = subtotal * rate;
        return value;
    }
}
