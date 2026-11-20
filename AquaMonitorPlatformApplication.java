package com.aquamonitor.platform.u20241e281;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Bootstrap class for the AquaMonitor Platform application.
 *
 * <p>Initializes Spring Boot auto-configuration and the JPA auditing infrastructure,
 * so auditable aggregate roots keep track of their creation and last update instants.</p>
 */
@EnableJpaAuditing
@SpringBootApplication
public class AquaMonitorPlatformApplication {

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AquaMonitorPlatformApplication.class, args);
    }
}