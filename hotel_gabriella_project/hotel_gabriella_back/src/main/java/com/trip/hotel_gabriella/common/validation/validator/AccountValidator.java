package com.trip.hotel_gabriella.common.validation.validator;

import com.trip.hotel_gabriella.common.validation.annotation.Account;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<Account,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }else {
            return value.matches("(?=.*\\d)(?=.*[a-zA-Z])([a-zA-Z0-9]{6,10})");
        }
    }
}
