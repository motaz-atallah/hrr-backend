package com.management.hrr.validators;

import com.management.hrr.validators.annotations.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
    private Enum<?>[] enumValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        enumValues = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are handled by @NotNull annotation
        }
        for (Enum<?> enumValue : enumValues) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}

