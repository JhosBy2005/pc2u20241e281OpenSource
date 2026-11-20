package com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.transform;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.resources.SamplingPlanResource;

import java.time.format.DateTimeFormatter;

/**
 * Assembler that converts a {@link SamplingPlan} aggregate into a {@link SamplingPlanResource}.
 */
public final class SamplingPlanResourceFromEntityAssembler {

    private SamplingPlanResourceFromEntityAssembler() {
    }

    /**
     * Converts the aggregate into its response resource, flattening its value objects.
     * Dates use the ISO 8601 format (yyyy-MM-dd).
     *
     * @param entity the sampling plan aggregate
     * @return the response resource
     */
    public static SamplingPlanResource toResourceFromEntity(SamplingPlan entity) {
        return new SamplingPlanResource(
                entity.getId(),
                entity.getPlanCode().code(),
                entity.getStationId().value().toString(),
                entity.getSamplingWindow().startDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                entity.getSamplingWindow().endDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                entity.getSampleVolume().quantity(),
                entity.getSampleVolume().unit(),
                entity.getPriorityLevel(),
                entity.getStatus().name(),
                entity.getSamplingNotes());
    }
}