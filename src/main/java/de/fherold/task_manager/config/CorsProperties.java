package de.fherold.task_manager.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for CORS (Cross-Origin Resource Sharing).
 * <p>
 * Binds the list of allowed origins from external configuration files (e.g. application.yml)
 * to be used in global CORS setup.
 * </p>
 * 
 * Example usage in application.yml:
 * <pre>
 * cors:
 *   allowed-origins:
 *     - http://localhost:5173
 *     - https://your-production-domain.com
 * </pre>
 */

@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private List<String> allowedOrigins = new ArrayList<>();

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
