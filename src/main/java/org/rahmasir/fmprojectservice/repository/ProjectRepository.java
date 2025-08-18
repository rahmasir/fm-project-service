package org.rahmasir.fmprojectservice.repository;

import org.rahmasir.fmprojectservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the Project entity.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
