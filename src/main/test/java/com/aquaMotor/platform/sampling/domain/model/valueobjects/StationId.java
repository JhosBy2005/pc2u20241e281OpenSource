package com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects;

import com.aquamonitor.platform.u20241e281.shared.domain.exceptions.DomainValidationException;

import java.util.UUID;

/**
 * StationId value object.
 *
 * <p>Identifier of a monitoring station. Stations are managed by another bounded context,
 * so this value object only keeps a reference to them through their UUID.</p>
 *
 * @param value the station UUID
 */
public record StationId(UUID value) {

    /**
     * Creates a station identifier validating that the UUID is present.
     *
     * @throws IllegalArgumentException when the UUID is null
     */
    public StationId {
        if (value == null) {
            throw new DomainValidationException("sampling-plan.station-id.required",
                    "Station id is required and must not be null");
        }
    }

    /**
     * Gets the identifier as string.
     *
     * @return the UUID as string
     */
    @Override
    public String toString() {
        return value.toString();
    }
}