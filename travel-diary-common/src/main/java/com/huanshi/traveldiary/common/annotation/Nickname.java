package com.huanshi.traveldiary.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NicknameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Nickname {
    String message() default "昵称格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
