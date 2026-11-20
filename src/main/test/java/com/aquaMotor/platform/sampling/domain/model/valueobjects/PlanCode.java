package com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects;

import com.aquamonitor.platform.u20241e281.shared.domain.exceptions.DomainValidationException;

/**
 * PlanCode value object.
 *
 * <p>Business code that identifies a sampling plan, for example {@code SAM-001}.
 * It is required and cannot be blank; surrounding white spaces are removed.</p>
 *
 * @param code the plan code
 */
public record PlanCode(String code) {

    /**
     * Creates a plan code validating that it is present.
     *
     * @throws IllegalArgumentException when the code is null or blank
     */
    public PlanCode {
        if (code == null || code.isBlank()) {
            throw new DomainValidationException("sampling-plan.plan-code.required",
                    "Plan code is required and must not be blank");
        }
        code = code.trim();
    }
}