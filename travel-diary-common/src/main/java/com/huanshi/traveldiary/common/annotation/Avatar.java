package com.huanshi.traveldiary.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AvatarValidator.class)
public @interface Avatar {
    String message() default "头像url格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
