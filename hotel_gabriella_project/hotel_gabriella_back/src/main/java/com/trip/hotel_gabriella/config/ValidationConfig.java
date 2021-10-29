package com.trip.hotel_gabriella.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validation;


@Configuration
@ComponentScan
public class ValidationConfig {
    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }
}
