package com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.converters;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.PlanCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts {@link PlanCode} values between the domain model and a database column.
 */
@Converter(autoApply = false)
public class PlanCodePersistenceConverter implements AttributeConverter<PlanCode, String> {

    /**
     * Converts the value object to its column value.
     *
     * @param attribute the plan code
     * @return the code as string, or {@code null}
     */
    @Override
    public String convertToDatabaseColumn(PlanCode attribute) {
        return attribute == null ? null : attribute.code();
    }

    /**
     * Converts the column value to the value object.
     *
     * @param dbData the stored code
     * @return the plan code, or {@code null}
     */
    @Override
    public PlanCode convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new PlanCode(dbData);
    }
}