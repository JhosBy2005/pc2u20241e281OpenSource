package com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

/**
 * Persistence representation of the {@code SamplingWindow} value object.
 */
@Embeddable
public class SamplingWindowPersistenceEmbeddable {

    @Column(name = "sampling_window_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "sampling_window_end_date", nullable = false)
    private LocalDate endDate;

    /**
     * Default constructor required by JPA.
     */
    protected SamplingWindowPersistenceEmbeddable() {
    }

    /**
     * Constructor.
     *
     * @param startDate the first day of the window
     * @param endDate   the last day of the window
     */
    public SamplingWindowPersistenceEmbeddable(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets the first day of the window.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the last day of the window.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }
}