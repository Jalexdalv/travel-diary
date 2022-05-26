package com.huanshi.traveldiary.common.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NicknameValidator implements ConstraintValidator<Nickname, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        value = StringUtils.trim(value);
        return value == null || (value.length() > 0 && value.length() <= 16);
    }
}