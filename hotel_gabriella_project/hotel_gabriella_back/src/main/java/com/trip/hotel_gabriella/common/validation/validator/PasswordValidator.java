package com.trip.hotel_gabriella.common.validation.validator;

import com.trip.hotel_gabriella.common.validation.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }else {
            return value.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&()\\-`.+,\"])([\\w!@#$&()\\-`.+,\"]{8,12})");
        }
    }
}
