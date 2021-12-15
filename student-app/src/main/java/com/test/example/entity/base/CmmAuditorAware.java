/*
 * (C) 2021 Dagangnet Technologies Sdn Bhd.
 */
package com.test.example.entity.base;

import com.test.example.utils.Constants;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 *
 * @author Kumaresan Sinniah
 */
public class CmmAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Constants.SYSTEM_USER);
    }

}
