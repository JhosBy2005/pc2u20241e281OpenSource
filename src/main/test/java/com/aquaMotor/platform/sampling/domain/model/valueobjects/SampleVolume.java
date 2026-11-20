package com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects;

import com.aquamonitor.platform.u20241e281.shared.domain.exceptions.DomainValidationException;

/**
 * SampleVolume value object.
 *
 * <p>Volume of water to be collected and its unit of measure, for example {@code 10 LITER}.
 * The quantity is a positive integer and the unit is required. No unit conversion is applied.</p>
 *
 * @param quantity the volume quantity, greater than 0
 * @param unit     the unit of measure
 */
public record SampleVolume(Integer quantity, String unit) {

    /**
     * Creates a sample volume validating its quantity and unit.
     *
     * @throws IllegalArgumentException when the quantity is null or not positive, or the unit is null or blank
     */
    public SampleVolume {
        if (quantity == null || quantity <= 0) {
            throw new DomainValidationException("sampling-plan.sample-volume.quantity.positive",
                    "Sample volume quantity is required and must be greater than 0");
        }
        if (unit == null || unit.isBlank()) {
            throw new DomainValidationException("sampling-plan.sample-volume.unit.required",
                    "Sample volume unit is required and must not be blank");
        }
        unit = unit.trim();
    }

    /**
     * Returns a new sample volume with the quantity increased and the same unit.
     * The current instance is not modified.
     *
     * @param additionalVolume the quantity to add, greater than 0
     * @return a new {@link SampleVolume} with the increased quantity
     * @throws IllegalArgumentException when the increment is null, not positive,
     *                                  or the result exceeds {@link Integer#MAX_VALUE}
     */
    public SampleVolume addVolume(Integer additionalVolume) {
        if (additionalVolume == null || additionalVolume <= 0) {
            throw new DomainValidationException("sampling-plan.sample-volume.additional-volume.positive",
                    "Additional volume is required and must be greater than 0");
        }
        try {
            return new SampleVolume(Math.addExact(quantity, additionalVolume), unit);
        } catch (ArithmeticException e) {
            throw new DomainValidationException("sampling-plan.sample-volume.overflow",
                    "Resulting sample volume must not exceed %d".formatted(Integer.MAX_VALUE),
                    String.valueOf(Integer.MAX_VALUE));
        }
    }
}