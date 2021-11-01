package com.trip.hotel_gabriella.common.validation.validator;

import com.trip.hotel_gabriella.common.validation.annotation.Agreement;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class AgreementValidator implements ConstraintValidator<Agreement,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null ) {
            return false;
        }else {
            if (value.equals("Y") || value.equals("N") ||
                    value.equals("y") || value.equals("y")) {
                return true;
            } else {
                return false;
            }
        }
    }
}
