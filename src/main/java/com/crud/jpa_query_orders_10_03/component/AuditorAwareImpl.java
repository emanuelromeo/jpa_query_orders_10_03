package com.crud.jpa_query_orders_10_03.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    // Customization of the auditor
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(getCurrentUsername());
    }

    // Returns the auditor username
    private String getCurrentUsername() {
        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            return authentication.getName();
        }
        return "system"; // fallback
    }
}
