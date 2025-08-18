package org.rahmasir.fmprojectservice.dto;

import org.rahmasir.fmsharedlib.enums.ProjectStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * A detailed DTO representing a single project.
 * This is used for the authenticated project detail endpoint.
 *
 * @param id          The project's unique ID.
 * @param name        The project's name.
 * @param description The project's description.
 * @param status      The current status of the project (e.g., ACTIVE, INACTIVE).
 * @param employerId  The UUID of the employer who created the project.
 * @param skills      The set of skills required for the project.
 * @param createdAt   The timestamp when the project was created.
 */
public record ProjectDetailsDto(
        UUID id,
        String name,
        String description,
        ProjectStatus status,
        UUID employerId,
        Set<SkillDto> skills,
        LocalDateTime createdAt
) {
}
