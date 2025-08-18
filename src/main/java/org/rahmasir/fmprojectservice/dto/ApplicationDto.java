package org.rahmasir.fmprojectservice.dto;

import org.rahmasir.fmsharedlib.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO representing a freelancer's application to a project.
 *
 * @param id           The application's unique ID.
 * @param projectId    The ID of the project being applied to.
 * @param freelancerId The ID of the freelancer who applied.
 * @param message      The message sent with the application.
 * @param status       The current status of the application.
 * @param createdAt    The timestamp when the application was submitted.
 */
public record ApplicationDto(
        UUID id,
        UUID projectId,
        UUID freelancerId,
        String message,
        ApplicationStatus status,
        LocalDateTime createdAt
) {
}
