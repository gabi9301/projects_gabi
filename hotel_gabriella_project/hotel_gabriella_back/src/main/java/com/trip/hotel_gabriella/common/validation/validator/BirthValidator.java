package com.trip.hotel_gabriella.common.validation.validator;

import com.trip.hotel_gabriella.common.validation.annotation.Birth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthValidator implements ConstraintValidator<Birth,String> {
    @Override
    public void initialize(Birth constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        return value.matches("[0-9]{6}");
    }
}
