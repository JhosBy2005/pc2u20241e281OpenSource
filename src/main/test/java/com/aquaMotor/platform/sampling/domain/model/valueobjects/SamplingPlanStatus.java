package com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects;

import com.aquamonitor.platform.u20241e281.shared.domain.exceptions.DomainValidationException;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Status of a sampling plan.
 */
public enum SamplingPlanStatus {
    /** The plan was registered and waits for approval. Default status. */
    PENDING,
    /** The plan was approved. */
    APPROVED,
    /** The samples were collected. */
    COMPLETED,
    /** The plan was cancelled. */
    CANCELLED;

    /**
     * Gets the status assigned when none is provided.
     *
     * @return {@link #PENDING}
     */
    public static SamplingPlanStatus defaultStatus() {
        return PENDING;
    }

    /**
     * Gets the status that matches the given name exactly.
     *
     * @param name the status name, for example {@code APPROVED}
     * @return the matching status
     * @throws IllegalArgumentException when the name is null or not a recognized status
     */
    public static SamplingPlanStatus fromName(String name) {
        if (name == null) {
            throw new DomainValidationException("sampling-plan.status.required",
                    "Status must not be null");
        }
        return Arrays.stream(values())
                .filter(status -> status.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new DomainValidationException("sampling-plan.status.invalid",
                        "Status '%s' is not valid. Allowed values: %s".formatted(name, allowedValues()),
                        name, allowedValues()));
    }

    /**
     * Gets the allowed status names separated by commas.
     *
     * @return the allowed status names
     */
    public static String allowedValues() {
        return Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", "));
    }
}