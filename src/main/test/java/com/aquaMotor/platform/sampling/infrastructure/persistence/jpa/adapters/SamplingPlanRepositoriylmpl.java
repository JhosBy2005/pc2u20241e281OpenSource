package com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.adapters;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.aggregates.SamplingPlan;
import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u20241e281.sampling.domain.repositories.SamplingPlanRepository;
import com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.assemblers.SamplingPlanPersistenceAssembler;
import com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.repositories.SamplingPlanPersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository adapter that implements the {@link SamplingPlanRepository} domain port with Spring Data JPA.
 */
@Repository
public class SamplingPlanRepositoryImpl implements SamplingPlanRepository {

    private final SamplingPlanPersistenceRepository persistenceRepository;

    /**
     * Constructor.
     *
     * @param persistenceRepository the Spring Data JPA repository
     */
    public SamplingPlanRepositoryImpl(SamplingPlanPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    // inherited javadoc
    @Override
    public SamplingPlan save(SamplingPlan samplingPlan) {
        var savedEntity = persistenceRepository.saveAndFlush(
                SamplingPlanPersistenceAssembler.toPersistenceFromDomain(samplingPlan));
        return SamplingPlanPersistenceAssembler.toDomainFromPersistence(savedEntity);
    }

    // inherited javadoc
    @Override
    public boolean existsByPlanCode(PlanCode planCode) {
        return persistenceRepository.existsByPlanCode(planCode);
    }
}