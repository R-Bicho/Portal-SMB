package com.portalSMB.portalSMB.model.repositories;

import com.portalSMB.portalSMB.model.entities.GenericFailure;
import com.portalSMB.portalSMB.model.entities.Voice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericFailureRepository extends JpaRepository<GenericFailure, Long> {
}
