package com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.resources;

import com.aquamonitor.platform.u20241e281.shared.interfaces.rest.validation.NotExplicitlyNull;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Request resource to register a new sampling plan.
 *
 * <p>The {@code status} attribute is a {@link JsonNullable}: when it is omitted the plan gets the
 * PENDING status, while sending it explicitly with {@code null} is rejected with HTTP 400.</p>
 *
 * @param planCode                the plan code
 * @param stationId               the monitoring station id (UUID)
 * @param samplingWindowStartDate the first day of the sampling window (yyyy-MM-dd)
 * @param samplingWindowEndDate   the last day of the sampling window (yyyy-MM-dd)
 * @param sampleVolumeQuantity    the volume quantity, greater than 0
 * @param sampleVolumeUnit        the volume unit
 * @param priorityLevel           the priority level, from 1 (lowest) to 5 (highest)
 * @param status                  the optional status (PENDING, APPROVED, COMPLETED or CANCELLED)
 * @param samplingNotes           optional notes
 */
@Schema(name = "CreateSamplingPlanRequest", description = "Request payload to register a sampling plan")
public record CreateSamplingPlanResource(
        @NotBlank(message = "{validation.not-blank}")
        @Size(max = 50, message = "{validation.size.max}")
        @Schema(description = "Plan code, unique", example = "SAM-001", requiredMode = Schema.RequiredMode.REQUIRED)
        String planCode,

        @NotNull(message = "{validation.not-null}")
        @Schema(description = "Monitoring station id", example = "550e8400-e29b-41d4-a716-446655440000",
                requiredMode = Schema.RequiredMode.REQUIRED)
        UUID stationId,

        @NotNull(message = "{validation.not-null}")
        @Schema(description = "Sampling window start date (yyyy-MM-dd)", example = "2026-11-21", type = "string",
                format = "date", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDate samplingWindowStartDate,

        @NotNull(message = "{validation.not-null}")
        @Schema(description = "Sampling window end date (yyyy-MM-dd), after the start date", example = "2026-11-25",
                type = "string", format = "date", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDate samplingWindowEndDate,

        @NotNull(message = "{validation.not-null}")
        @Schema(description = "Sample volume quantity, greater than 0", example = "10",
                requiredMode = Schema.RequiredMode.REQUIRED)
        Integer sampleVolumeQuantity,

        @NotBlank(message = "{validation.not-blank}")
        @Size(max = 50, message = "{validation.size.max}")
        @Schema(description = "Sample volume unit", example = "LITER", requiredMode = Schema.RequiredMode.REQUIRED)
        String sampleVolumeUnit,

        @NotNull(message = "{validation.not-null}")
        @Schema(description = "Priority level from 1 (lowest) to 5 (highest)", example = "3",
                requiredMode = Schema.RequiredMode.REQUIRED)
        Integer priorityLevel,

        @NotExplicitlyNull
        @Schema(implementation = String.class, description = "Optional status, PENDING when omitted",
                example = "PENDING", allowableValues = {"PENDING", "APPROVED", "COMPLETED", "CANCELLED"},
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        JsonNullable<String> status,

        @Schema(description = "Optional notes", example = "Water quality sampling for river monitoring",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String samplingNotes) {

    /**
     * Creates the resource, treating a missing {@code status} container as an omitted attribute.
     */
    public CreateSamplingPlanResource {
        if (status == null) {
            status = JsonNullable.undefined();
        }
    }
}