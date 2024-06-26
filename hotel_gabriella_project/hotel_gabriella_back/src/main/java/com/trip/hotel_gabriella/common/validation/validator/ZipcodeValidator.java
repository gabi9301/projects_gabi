package com.trip.hotel_gabriella.common.validation.validator;

import com.trip.hotel_gabriella.common.validation.annotation.Zipcode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipcodeValidator implements ConstraintValidator<Zipcode,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }else {
            return value.matches("\\d{5}");
        }
    }
}
