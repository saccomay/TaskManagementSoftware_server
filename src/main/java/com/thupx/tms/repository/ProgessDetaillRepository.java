package com.thupx.tms.repository;

import com.thupx.tms.domain.ProgessDetaill;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProgessDetaill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgessDetaillRepository extends JpaRepository<ProgessDetaill, Long> {
}
