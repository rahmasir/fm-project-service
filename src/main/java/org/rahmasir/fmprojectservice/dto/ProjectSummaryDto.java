package org.rahmasir.fmprojectservice.dto;

import java.util.Set;
import java.util.UUID;

/**
 * A summarized DTO for project listings.
 * This is used for the public, unauthenticated project list endpoint.
 *
 * @param id     The project's unique ID.
 * @param name   The project's name.
 * @param skills The skills required for the project.
 */
public record ProjectSummaryDto(
        UUID id,
        String name,
        Set<SkillDto> skills
) {
}
