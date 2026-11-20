package com.aquamonitor.platform.u20241e281.sampling.application.internal.commandservices;

import com.aquamonitor.platform.u20241e281.sampling.application.commands.CreateSamplingPlanCommand;
import com.aquamonitor.platform.u20241e281.sampling.application.commandservices.SamplingPlanCommandService;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.SampleVolume;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.SamplingPlanStatus;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.SamplingWindow;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.StationId;
import com.aquamonitor.platform.u20241e281.sampling.domain.repositories.SamplingPlanRepository;
import com.aquamonitor.platform.u20241e281.shared.application.result.ApplicationError;
import com.aquamonitor.platform.u20241e281.shared.application.result.Result;
import com.aquamonitor.platform.u20241e281.shared.domain.exceptions.DomainValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link SamplingPlanCommandService}.
 *
 * <p>Builds the value objects and the aggregate (which enforce the business rules), checks
 * the uniqueness of the plan code and persists the plan. The UNIQUE constraint of the database
 * is the last line of defense: if it is violated, a conflict error is returned as well.</p>
 */
@Service
public class SamplingPlanCommandServiceImpl implements SamplingPlanCommandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SamplingPlanCommandServiceImpl.class);

    private final SamplingPlanRepository samplingPlanRepository;

    /**
     * Constructor.
     *
     * @param samplingPlanRepository the {@link SamplingPlanRepository}
     */
    public SamplingPlanCommandServiceImpl(SamplingPlanRepository samplingPlanRepository) {
        this.samplingPlanRepository = samplingPlanRepository;
    }

    // inherited javadoc
    @Override
    public Result<SamplingPlan, ApplicationError> handle(CreateSamplingPlanCommand command) {
        try {
            var samplingPlan = toSamplingPlan(command);
            if (samplingPlanRepository.existsByPlanCode(samplingPlan.getPlanCode())) {
                return Result.failure(duplicatedPlanCode(samplingPlan.getPlanCode()));
            }
            return Result.success(samplingPlanRepository.save(samplingPlan));
        } catch (DomainValidationException e) {
            return Result.failure(ApplicationError.validationError(e.getMessageKey(), e.getMessage(), e.getArguments()));
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("error.request.validation-failed", e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Unique constraint violated while saving sampling plan {}", command.planCode());
            return Result.failure(duplicatedPlanCode(new PlanCode(command.planCode())));
        } catch (Exception e) {
            LOGGER.error("Unexpected error while creating sampling plan {}", command.planCode(), e);
            return Result.failure(ApplicationError.unexpected());
        }
    }

    private static SamplingPlan toSamplingPlan(CreateSamplingPlanCommand command) {
        var planCode = new PlanCode(command.planCode());
        var stationId = new StationId(command.stationId());
        var samplingWindow = new SamplingWindow(command.samplingWindowStartDate(), command.samplingWindowEndDate());
        var sampleVolume = new SampleVolume(command.sampleVolumeQuantity(), command.sampleVolumeUnit());
        if (command.status() == null) {
            return new SamplingPlan(planCode, stationId, samplingWindow, sampleVolume, command.priorityLevel(),
                    command.samplingNotes());
        }
        return new SamplingPlan(planCode, stationId, samplingWindow, sampleVolume, command.priorityLevel(),
                SamplingPlanStatus.fromName(command.status()), command.samplingNotes());
    }

    private static ApplicationError duplicatedPlanCode(PlanCode planCode) {
        return ApplicationError.conflict("sampling-plan.plan-code.duplicated",
                "A sampling plan with plan code '%s' already exists".formatted(planCode.code()),
                planCode.code());
    }
}