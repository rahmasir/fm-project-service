package org.rahmasir.fmprojectservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

/**
 * DTO for creating a new project.
 *
 * @param name        The name of the project.
 * @param description A detailed description of the project.
 * @param skills      A set of skill names required for the project.
 */
public record CreateProjectRequest(
        @NotBlank(message = "Project name cannot be blank")
        @Size(max = 100, message = "Project name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Project description cannot be blank")
        String description,

        @NotEmpty(message = "A project must have at least one skill")
        Set<String> skills
) {
}
