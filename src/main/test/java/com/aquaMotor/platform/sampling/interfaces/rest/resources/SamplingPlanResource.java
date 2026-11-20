package com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response resource with the information of a sampling plan.
 *
 * @param id                      the database id
 * @param planCode                the plan code
 * @param stationId               the monitoring station id
 * @param samplingWindowStartDate the first day of the sampling window (yyyy-MM-dd)
 * @param samplingWindowEndDate   the last day of the sampling window (yyyy-MM-dd)
 * @param sampleVolumeQuantity    the volume quantity
 * @param sampleVolumeUnit        the volume unit
 * @param priorityLevel           the priority level
 * @param status                  the status
 * @param samplingNotes           the notes
 */
@Schema(name = "SamplingPlanResponse", description = "Sampling plan information")
public record SamplingPlanResource(
        @Schema(example = "1") Long id,
        @Schema(example = "SAM-001") String planCode,
        @Schema(example = "550e8400-e29b-41d4-a716-446655440000") String stationId,
        @Schema(example = "2026-11-21") String samplingWindowStartDate,
        @Schema(example = "2026-11-25") String samplingWindowEndDate,
        @Schema(example = "10") Integer sampleVolumeQuantity,
        @Schema(example = "LITER") String sampleVolumeUnit,
        @Schema(example = "3") Integer priorityLevel,
        @Schema(example = "PENDING") String status,
        @Schema(example = "Water quality sampling for river monitoring") String samplingNotes) {
}