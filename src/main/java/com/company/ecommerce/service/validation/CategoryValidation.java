package com.company.ecommerce.service.validation;

import org.springframework.stereotype.Component;

@Component
public class CategoryValidation {
    public boolean validateCategory(String str) {
        return str != null && !str.isEmpty() && Character.isUpperCase(str.charAt(0));
    }
}
