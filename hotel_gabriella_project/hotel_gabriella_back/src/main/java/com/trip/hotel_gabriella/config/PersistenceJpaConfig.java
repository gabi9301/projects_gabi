package com.trip.hotel_gabriella.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@ComponentScan
@RequiredArgsConstructor
public class PersistenceJpaConfig {

    //@PersistenceContext
    //private final EntityManager em;

}
