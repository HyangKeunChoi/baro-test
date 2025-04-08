package com.example.barotest.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SearchDateValidator implements ConstraintValidator<SearchDateRangeValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        try {
            LocalDate startDate = LocalDate.parse(value.split(",")[0].trim());
            LocalDate endDate = LocalDate.parse(value.split(",")[1].trim());

            if (startDate.isAfter(endDate)) {
                return false;
            }

            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            return daysBetween <= 3;
        } catch (Exception e) {
            return false;
        }
    }
}
