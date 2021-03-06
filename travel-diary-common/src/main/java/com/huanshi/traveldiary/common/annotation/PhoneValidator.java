package com.huanshi.traveldiary.common.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && Pattern.matches("/^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$/", Long.toString(value));
    }
}