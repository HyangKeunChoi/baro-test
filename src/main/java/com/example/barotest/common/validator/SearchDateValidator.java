package com.example.barotest.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SearchDateValidator implements ConstraintValidator<SearchDateRangeValid, String> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate startDate = LocalDate.now(); // 또는 요청에서 받은 startDate를 사용
        LocalDate endDate = value;

        if (startDate.isAfter(endDate)) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return daysBetween <= 3;
    }
}
