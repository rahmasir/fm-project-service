package org.rahmasir.fmprojectservice.repository;

import org.rahmasir.fmprojectservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the Application entity.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
}
