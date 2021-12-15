/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.example.config;

import com.test.example.entity.base.CmmAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *
 * @author Kumaresan Sinniah
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")

public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new CmmAuditorAware();
    }

}
