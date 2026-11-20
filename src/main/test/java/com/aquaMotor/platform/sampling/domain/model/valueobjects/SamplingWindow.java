package com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects;

import com.aquamonitor.platform.u20241e281.shared.domain.exceptions.DomainValidationException;

import java.time.LocalDate;

/**
 * SamplingWindow value object.
 *
 * <p>Period of time in which the water samples must be collected. Both dates are required
 * and the end date must be after the start date.</p>
 *
 * @param startDate the first day of the window
 * @param endDate   the last day of the window
 */
public record SamplingWindow(LocalDate startDate, LocalDate endDate) {

    /**
     * Creates a sampling window validating its dates.
     *
     * @throws IllegalArgumentException when a date is null or the end date is not after the start date
     */
    public SamplingWindow {
        if (startDate == null) {
            throw new DomainValidationException("sampling-plan.sampling-window.start-date.required",
                    "Sampling window start date is required and must not be null");
        }
        if (endDate == null) {
            throw new DomainValidationException("sampling-plan.sampling-window.end-date.required",
                    "Sampling window end date is required and must not be null");
        }
        if (!endDate.isAfter(startDate)) {
            throw new DomainValidationException("sampling-plan.sampling-window.end-date.after-start-date",
                    "Sampling window end date must be after the start date");
        }
    }

    /**
     * Checks whether a date is inside the sampling window, both limits included.
     *
     * @param checkDate the date to check
     * @return {@code true} when the date is between the start and end dates (inclusive), {@code false} otherwise
     * @throws IllegalArgumentException when the date is null
     */
    public boolean contains(LocalDate checkDate) {
        if (checkDate == null) {
            throw new DomainValidationException("sampling-plan.sampling-window.check-date.required",
                    "Date to check is required and must not be null");
        }
        return !checkDate.isBefore(startDate) && !checkDate.isAfter(endDate);
    }
}