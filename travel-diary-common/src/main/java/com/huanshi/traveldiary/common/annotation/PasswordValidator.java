package com.huanshi.traveldiary.common.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        value = StringUtils.trimToNull(value);
        return value != null && value.length() >= 8 && value.length() <= 32;
    }
}