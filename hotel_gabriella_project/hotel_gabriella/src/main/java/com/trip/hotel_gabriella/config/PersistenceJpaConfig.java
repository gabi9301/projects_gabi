package com.trip.hotel_gabriella.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@ComponentScan
public class PersistenceJpaConfig {

    @PersistenceContext
    private final EntityManager em;

    public PersistenceJpaConfig(EntityManager em) {
        this.em = em;
    }
}
