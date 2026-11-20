package com.aquamonitor.platform.u20241e281.sampling.domain.repositories;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.PlanCode;

/**
 * Repository port for the {@link SamplingPlan} aggregate.
 */
public interface SamplingPlanRepository {

    /**
     * Saves a sampling plan.
     *
     * @param samplingPlan the aggregate to save
     * @return the saved aggregate with its generated id and audit instants
     */
    SamplingPlan save(SamplingPlan samplingPlan);

    /**
     * Checks whether a sampling plan with the given plan code already exists.
     *
     * @param planCode the plan code
     * @return {@code true} when a plan with the same code exists
     */
    boolean existsByPlanCode(PlanCode planCode);
}