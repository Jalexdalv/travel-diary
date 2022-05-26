package com.huanshi.traveldiary.common.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SmsVerifyCodeValidator implements ConstraintValidator<SmsVerifyCode, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null && value >= 10000 && value <= 99999;
    }
}