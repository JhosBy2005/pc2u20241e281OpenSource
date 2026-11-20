package com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.converters;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.StationId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

/**
 * Converts {@link StationId} values between the domain model and a database column.
 */
@Converter(autoApply = false)
public class StationIdPersistenceConverter implements AttributeConverter<StationId, String> {

    /**
     * Converts the value object to its column value.
     *
     * @param attribute the station id
     * @return the UUID as string, or {@code null}
     */
    @Override
    public String convertToDatabaseColumn(StationId attribute) {
        return attribute == null ? null : attribute.value().toString();
    }

    /**
     * Converts the column value to the value object.
     *
     * @param dbData the stored UUID
     * @return the station id, or {@code null}
     */
    @Override
    public StationId convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new StationId(UUID.fromString(dbData));
    }
}