package com.example.barotest.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SearchDateValidator.class)
public @interface SearchDateRangeValid {
    String message() default "시작일보다 클수 없고 최대 3일까지만 초회 가능합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String startDate(); // 검색 시작일 필드명

    String endDate();   // 검색 종료일 필드명
}
