package com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Persistence representation of the {@code SampleVolume} value object.
 */
@Embeddable
public class SampleVolumePersistenceEmbeddable {

    @Column(name = "sample_volume_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "sample_volume_unit", nullable = false, length = 50)
    private String unit;

    /**
     * Default constructor required by JPA.
     */
    protected SampleVolumePersistenceEmbeddable() {
    }

    /**
     * Constructor.
     *
     * @param quantity the volume quantity
     * @param unit     the volume unit
     */
    public SampleVolumePersistenceEmbeddable(Integer quantity, String unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Gets the volume quantity.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Gets the volume unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }
}