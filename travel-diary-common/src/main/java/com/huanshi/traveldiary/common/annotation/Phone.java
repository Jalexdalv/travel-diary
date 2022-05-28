package com.huanshi.traveldiary.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Phone {
    String message() default "手机号码格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
