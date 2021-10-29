package com.trip.hotel_gabriella.common.validation.annotation;

import com.trip.hotel_gabriella.common.validation.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {

    String message() default "메일 주소";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    public boolean nullable() default false;
}
