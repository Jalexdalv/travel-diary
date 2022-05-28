package com.huanshi.traveldiary.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SmsVerifyCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface SmsVerifyCode {
    String message() default "短信验证码格式不正确";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
