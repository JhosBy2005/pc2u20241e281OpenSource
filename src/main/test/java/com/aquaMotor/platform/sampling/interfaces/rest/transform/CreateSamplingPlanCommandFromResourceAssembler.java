package com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.transform;

import com.aquamonitor.platform.u20241e281.sampling.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.resources.CreateSamplingPlanResource;

/**
 * Assembler that converts a {@link CreateSamplingPlanResource} into a {@link CreateSamplingPlanCommand}.
 */
public final class CreateSamplingPlanCommandFromResourceAssembler {

    private CreateSamplingPlanCommandFromResourceAssembler() {
    }

    /**
     * Converts the request resource into a command.
     * An omitted status becomes {@code null}, so the default status is assigned by the domain.
     *
     * @param resource the request resource
     * @return the create command
     */
    public static CreateSamplingPlanCommand toCommandFromResource(CreateSamplingPlanResource resource) {
        return new CreateSamplingPlanCommand(
                resource.planCode(),
                resource.stationId(),
                resource.samplingWindowStartDate(),
                resource.samplingWindowEndDate(),
                resource.sampleVolumeQuantity(),
                resource.sampleVolumeUnit(),
                resource.priorityLevel(),
                resource.status().orElse(null),
                resource.samplingNotes());
    }
}