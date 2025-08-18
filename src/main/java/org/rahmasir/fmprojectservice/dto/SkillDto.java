package org.rahmasir.fmprojectservice.dto;

import java.util.UUID;

/**
 * A DTO to represent a Skill in the context of a project.
 *
 * @param id   The unique identifier of the skill.
 * @param name The name of the skill.
 */
public record SkillDto(UUID id, String name) {
}
