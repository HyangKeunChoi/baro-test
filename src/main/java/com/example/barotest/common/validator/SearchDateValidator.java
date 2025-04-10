package com.example.barotest.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SearchDateValidator implements ConstraintValidator<SearchDateRangeValid, Object> {

    private String startDateFieldName;
    private String endDateFieldName;

    @Override
    public void initialize(SearchDateRangeValid constraintAnnotation) {
        this.startDateFieldName = constraintAnnotation.startDate();
        this.endDateFieldName = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        LocalDate startDate = (LocalDate) beanWrapper.getPropertyValue(startDateFieldName);
        LocalDate endDate = (LocalDate) beanWrapper.getPropertyValue(endDateFieldName);

        if (startDate == null || endDate == null) {
            return true;
        }

        if (startDate.isAfter(endDate)) {
            return false;
        }

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return daysBetween < 3;
    }
}
