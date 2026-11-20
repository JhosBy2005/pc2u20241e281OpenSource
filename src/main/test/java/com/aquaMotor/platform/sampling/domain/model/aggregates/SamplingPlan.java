package com.aquamonitor.platform.u20241e281.sampling.domain.model.aggregates;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.SampleVolume;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.SamplingPlanStatus;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.SamplingWindow;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.StationId;
import com.aquamonitor.platform.u20241e281.shared.domain.exceptions.DomainValidationException;
import com.aquamonitor.platform.u20241e281.shared.domain.model.aggregates.AbstractDomainAggregateRoot;

import java.time.Instant;

/**
 * SamplingPlan aggregate root.
 *
 * <p>Plan to collect water samples at a monitoring station during a sampling window.
 * It is auditable: {@code createdAt} and {@code updatedAt} are recorded with the creation instant
 * by the persistence layer. No JPA annotation is present here; persistence concerns live in the
 * infrastructure layer.</p>
 */
public class SamplingPlan extends AbstractDomainAggregateRoot<SamplingPlan> {

    /** Lowest priority level. */
    public static final int MIN_PRIORITY_LEVEL = 1;

    /** Highest priority level. */
    public static final int MAX_PRIORITY_LEVEL = 5;

    private final Long id;
    private final PlanCode planCode;
    private final StationId stationId;
    private final SamplingWindow samplingWindow;
    private final SampleVolume sampleVolume;
    private final Integer priorityLevel;
    private final SamplingPlanStatus status;
    private final String samplingNotes;
    private final Instant createdAt;
    private final Instant updatedAt;

    private SamplingPlan(Long id, PlanCode planCode, StationId stationId, SamplingWindow samplingWindow,
                         SampleVolume sampleVolume, Integer priorityLevel, SamplingPlanStatus status,
                         String samplingNotes, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.planCode = requireValue(planCode, "sampling-plan.plan-code.required",
                "Plan code is required and must not be blank");
        this.stationId = requireValue(stationId, "sampling-plan.station-id.required",
                "Station id is required and must not be null");
        this.samplingWindow = requireValue(samplingWindow, "sampling-plan.sampling-window.required",
                "Sampling window is required");
        this.sampleVolume = requireValue(sampleVolume, "sampling-plan.sample-volume.required",
                "Sample volume is required");
        this.priorityLevel = validatePriorityLevel(priorityLevel);
        this.status = requireValue(status, "sampling-plan.status.required", "Status must not be null");
        this.samplingNotes = samplingNotes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Registers a new sampling plan with the default status ({@link SamplingPlanStatus#PENDING}).
     *
     * @param planCode       the plan code
     * @param stationId      the monitoring station id
     * @param samplingWindow the sampling window
     * @param sampleVolume   the volume of water to collect
     * @param priorityLevel  the priority level, from 1 (lowest) to 5 (highest)
     * @param samplingNotes  optional notes
     */
    public SamplingPlan(PlanCode planCode, StationId stationId, SamplingWindow samplingWindow,
                        SampleVolume sampleVolume, Integer priorityLevel, String samplingNotes) {
        this(planCode, stationId, samplingWindow, sampleVolume, priorityLevel,
                SamplingPlanStatus.defaultStatus(), samplingNotes);
    }

    /**
     * Registers a new sampling plan with the given status.
     *
     * @param planCode       the plan code
     * @param stationId      the monitoring station id
     * @param samplingWindow the sampling window
     * @param sampleVolume   the volume of water to collect
     * @param priorityLevel  the priority level, from 1 (lowest) to 5 (highest)
     * @param status         the initial status
     * @param samplingNotes  optional notes
     */
    public SamplingPlan(PlanCode planCode, StationId stationId, SamplingWindow samplingWindow,
                        SampleVolume sampleVolume, Integer priorityLevel, SamplingPlanStatus status,
                        String samplingNotes) {
        this(null, planCode, stationId, samplingWindow, sampleVolume, priorityLevel, status, samplingNotes,
                null, null);
    }

    /**
     * Reconstitutes an existing sampling plan from its persisted state.
     *
     * @param id             the database identifier
     * @param planCode       the plan code
     * @param stationId      the monitoring station id
     * @param samplingWindow the sampling window
     * @param sampleVolume   the volume of water to collect
     * @param priorityLevel  the priority level
     * @param status         the status
     * @param samplingNotes  the notes
     * @param createdAt      the creation instant
     * @param updatedAt      the last update instant
     * @return the reconstituted sampling plan
     */
    public static SamplingPlan reconstitute(Long id, PlanCode planCode, StationId stationId,
                                            SamplingWindow samplingWindow, SampleVolume sampleVolume,
                                            Integer priorityLevel, SamplingPlanStatus status, String samplingNotes,
                                            Instant createdAt, Instant updatedAt) {
        return new SamplingPlan(id, planCode, stationId, samplingWindow, sampleVolume, priorityLevel, status,
                samplingNotes, createdAt, updatedAt);
    }

    private static Integer validatePriorityLevel(Integer priorityLevel) {
        if (priorityLevel == null || priorityLevel < MIN_PRIORITY_LEVEL || priorityLevel > MAX_PRIORITY_LEVEL) {
            throw new DomainValidationException("sampling-plan.priority-level.range",
                    "Priority level is required and must be between %d and %d"
                            .formatted(MIN_PRIORITY_LEVEL, MAX_PRIORITY_LEVEL),
                    MIN_PRIORITY_LEVEL, MAX_PRIORITY_LEVEL);
        }
        return priorityLevel;
    }

    private static <T> T requireValue(T value, String messageKey, String defaultMessage) {
        if (value == null) {
            throw new DomainValidationException(messageKey, defaultMessage);
        }
        return value;
    }

    /**
     * Gets the database identifier.
     *
     * @return the identifier, {@code null} before the plan is persisted
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the plan code.
     *
     * @return the plan code
     */
    public PlanCode getPlanCode() {
        return planCode;
    }

    /**
     * Gets the monitoring station id.
     *
     * @return the station id
     */
    public StationId getStationId() {
        return stationId;
    }

    /**
     * Gets the sampling window.
     *
     * @return the sampling window
     */
    public SamplingWindow getSamplingWindow() {
        return samplingWindow;
    }

    /**
     * Gets the volume of water to collect.
     *
     * @return the sample volume
     */
    public SampleVolume getSampleVolume() {
        return sampleVolume;
    }

    /**
     * Gets the priority level, from 1 (lowest) to 5 (highest).
     *
     * @return the priority level
     */
    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public SamplingPlanStatus getStatus() {
        return status;
    }

    /**
     * Gets the optional notes.
     *
     * @return the notes, or {@code null}
     */
    public String getSamplingNotes() {
        return samplingNotes;
    }

    /**
     * Gets the creation instant.
     *
     * @return the creation instant, {@code null} before the plan is persisted
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the last update instant.
     *
     * @return the last update instant, {@code null} before the plan is persisted
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }
}