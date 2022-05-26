package com.huanshi.traveldiary.common.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AvatarValidator implements ConstraintValidator<Avatar, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        value = StringUtils.trim(value);
        return value == null || (value.length() > 0 && value.length() <= 255 && Pattern.matches("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$", value));
    }
}