package com.aquamonitor.platform.u20241e281.sampling.application.commands;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Command to register a new sampling plan.
 *
 * @param planCode                the plan code
 * @param stationId               the monitoring station id
 * @param samplingWindowStartDate the first day of the sampling window
 * @param samplingWindowEndDate   the last day of the sampling window
 * @param sampleVolumeQuantity    the volume quantity
 * @param sampleVolumeUnit        the volume unit
 * @param priorityLevel           the priority level, from 1 to 5
 * @param status                  the status name, or {@code null} when it was omitted (PENDING is assigned)
 * @param samplingNotes           optional notes
 */
public record CreateSamplingPlanCommand(
        String planCode,
        UUID stationId,
        LocalDate samplingWindowStartDate,
        LocalDate samplingWindowEndDate,
        Integer sampleVolumeQuantity,
        String sampleVolumeUnit,
        Integer priorityLevel,
        String status,
        String samplingNotes) {
}



