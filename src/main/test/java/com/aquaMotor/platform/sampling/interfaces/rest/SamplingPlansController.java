package com.aquamonitor.platform.u20241e281.sampling.interfaces.rest;

import com.aquamonitor.platform.u20241e281.sampling.application.commandservices.SamplingPlanCommandService;
import com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.resources.CreateSamplingPlanResource;
import com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.resources.SamplingPlanResource;
import com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.transform.CreateSamplingPlanCommandFromResourceAssembler;
import com.aquamonitor.platform.u20241e281.sampling.interfaces.rest.transform.SamplingPlanResourceFromEntityAssembler;
import com.aquamonitor.platform.u20241e281.shared.interfaces.rest.resources.MessageResource;
import com.aquamonitor.platform.u20241e281.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes the sampling plans endpoint.
 */
@RestController
@RequestMapping(value = "/api/v1/sampling-plans", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sampling Plans", description = "Water sampling plan management endpoints")
public class SamplingPlansController {

    private final SamplingPlanCommandService samplingPlanCommandService;
    private final MessageSource messageSource;

    /**
     * Constructor.
     *
     * @param samplingPlanCommandService the {@link SamplingPlanCommandService}
     * @param messageSource              the source of localized messages
     */
    public SamplingPlansController(SamplingPlanCommandService samplingPlanCommandService,
                                   MessageSource messageSource) {
        this.samplingPlanCommandService = samplingPlanCommandService;
        this.messageSource = messageSource;
    }

    /**
     * Registers a new sampling plan.
     *
     * @param resource the {@link CreateSamplingPlanResource} request body
     * @return 201 with the created {@link SamplingPlanResource}, or an error {@link MessageResource}
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Register a sampling plan",
            description = "Registers a new water sampling plan for a monitoring station. The id is generated "
                    + "automatically and the status is PENDING when omitted. Error messages are localized "
                    + "with the Accept-Language header (en, es, es-PE).")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sampling plan created",
                    content = @Content(schema = @Schema(implementation = SamplingPlanResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = MessageResource.class))),
            @ApiResponse(responseCode = "409", description = "Duplicated plan code",
                    content = @Content(schema = @Schema(implementation = MessageResource.class))),
            @ApiResponse(responseCode = "500", description = "Unexpected error",
                    content = @Content(schema = @Schema(implementation = MessageResource.class)))
    })
    public ResponseEntity<?> createSamplingPlan(@Valid @RequestBody CreateSamplingPlanResource resource) {
        var command = CreateSamplingPlanCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = samplingPlanCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SamplingPlanResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED,
                messageSource);
    }
}