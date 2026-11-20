package com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.repositories;

import com.aquamonitor.platform.u20241e281.sampling.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u20241e281.sampling.infrastructure.persistence.jpa.entities.SamplingPlanPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for {@link SamplingPlanPersistenceEntity}.
 */
@Repository
public interface SamplingPlanPersistenceRepository extends JpaRepository<SamplingPlanPersistenceEntity, Long> {

    /**
     * Checks whether a sampling plan with the given plan code exists.
     *
     * @param planCode the plan code
     * @return {@code true} when a plan with the same code exists
     */
    boolean existsByPlanCode(PlanCode planCode);
}