package org.rahmasir.fmprojectservice.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for a freelancer to apply to a project.
 *
 * @param message The message or cover letter sent with the application.
 */
public record ApplyToProjectRequest(
        @NotBlank(message = "Application message cannot be blank")
        String message
) {
}
