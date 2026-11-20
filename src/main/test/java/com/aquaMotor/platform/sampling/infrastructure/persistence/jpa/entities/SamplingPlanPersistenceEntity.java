package com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.entities;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.SamplingPlanStatus;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.StationId;
import com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.converters.PlanCodePersistenceConverter;
import com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.converters.StationIdPersistenceConverter;
import com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.embeddables.SampleVolumePersistenceEmbeddable;
import com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.embeddables.SamplingWindowPersistenceEmbeddable;
import com.aquamonitor.platform.u20241e281.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * JPA persistence entity for sampling plans.
 *
 * <p>Inherits {@code id}, {@code createdAt} and {@code updatedAt} from
 * {@link AuditableAbstractPersistenceEntity}. The plan code is protected by a UNIQUE constraint.
 * Table and column names follow the snake case convention of the shared physical naming strategy.</p>
 */
@Entity
@Table(name = "sampling_plans",
        uniqueConstraints = @UniqueConstraint(name = "uk_sampling_plans_plan_code", columnNames = "plan_code"))
public class SamplingPlanPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = PlanCodePersistenceConverter.class)
    @Column(name = "plan_code", nullable = false, length = 50)
    private PlanCode planCode;

    @Convert(converter = StationIdPersistenceConverter.class)
    @Column(name = "station_id", nullable = false, length = 36)
    private StationId stationId;

    @Embedded
    private SamplingWindowPersistenceEmbeddable samplingWindow;

    @Embedded
    private SampleVolumePersistenceEmbeddable sampleVolume;

    @Column(nullable = false)
    private Integer priorityLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SamplingPlanStatus status;

    @Column(columnDefinition = "TEXT")
    private String samplingNotes;

    /**
     * Gets the plan code.
     *
     * @return the plan code
     */
    public PlanCode getPlanCode() {
        return planCode;
    }

    /**
     * Sets the plan code.
     *
     * @param planCode the plan code
     */
    public void setPlanCode(PlanCode planCode) {
        this.planCode = planCode;
    }

    /**
     * Gets the station id.
     *
     * @return the station id
     */
    public StationId getStationId() {
        return stationId;
    }

    /**
     * Sets the station id.
     *
     * @param stationId the station id
     */
    public void setStationId(StationId stationId) {
        this.stationId = stationId;
    }

    /**
     * Gets the sampling window.
     *
     * @return the sampling window
     */
    public SamplingWindowPersistenceEmbeddable getSamplingWindow() {
        return samplingWindow;
    }

    /**
     * Sets the sampling window.
     *
     * @param samplingWindow the sampling window
     */
    public void setSamplingWindow(SamplingWindowPersistenceEmbeddable samplingWindow) {
        this.samplingWindow = samplingWindow;
    }

    /**
     * Gets the sample volume.
     *
     * @return the sample volume
     */
    public SampleVolumePersistenceEmbeddable getSampleVolume() {
        return sampleVolume;
    }

    /**
     * Sets the sample volume.
     *
     * @param sampleVolume the sample volume
     */
    public void setSampleVolume(SampleVolumePersistenceEmbeddable sampleVolume) {
        this.sampleVolume = sampleVolume;
    }

    /**
     * Gets the priority level.
     *
     * @return the priority level
     */
    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Sets the priority level.
     *
     * @param priorityLevel the priority level
     */
    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
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
     * Sets the status.
     *
     * @param status the status
     */
    public void setStatus(SamplingPlanStatus status) {
        this.status = status;
    }

    /**
     * Gets the notes.
     *
     * @return the notes
     */
    public String getSamplingNotes() {
        return samplingNotes;
    }

    /**
     * Sets the notes.
     *
     * @param samplingNotes the notes
     */
    public void setSamplingNotes(String samplingNotes) {
        this.samplingNotes = samplingNotes;
    }
}