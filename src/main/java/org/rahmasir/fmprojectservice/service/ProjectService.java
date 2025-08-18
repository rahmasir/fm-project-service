package org.rahmasir.fmprojectservice.service;

import org.rahmasir.fmprojectservice.dto.*;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for project and application-related business logic.
 */
public interface ProjectService {

    /**
     * Creates a new project.
     *
     * @param request    The DTO containing project details.
     * @param employerId The UUID of the employer creating the project.
     * @return A DTO representing the newly created project.
     */
    ProjectDetailsDto createProject(CreateProjectRequest request, UUID employerId);

    /**
     * Retrieves a list of all active projects with summary information.
     *
     * @return A list of project summary DTOs.
     */
    List<ProjectSummaryDto> listAllProjects();

    /**
     * Retrieves the full details of a specific project.
     *
     * @param projectId The UUID of the project.
     * @return A DTO with the project's full details.
     */
    ProjectDetailsDto getProjectDetails(UUID projectId);

    /**
     * Allows a freelancer to apply for a project.
     *
     * @param projectId    The UUID of the project to apply for.
     * @param freelancerId The UUID of the applying freelancer.
     * @param request      The DTO containing the application message.
     * @return A DTO representing the newly created application.
     */
    ApplicationDto applyToProject(UUID projectId, UUID freelancerId, ApplyToProjectRequest request);

    /**
     * Retrieves all applications for a specific project.
     *
     * @param projectId  The UUID of the project.
     * @param employerId The UUID of the employer requesting the applications (for authorization).
     * @return A list of application DTOs.
     */
    List<ApplicationDto> viewApplications(UUID projectId, UUID employerId);

    /**
     * Accepts an application for a project.
     *
     * @param projectId     The UUID of the project.
     * @param applicationId The UUID of the application to accept.
     * @param employerId    The UUID of the employer performing the action (for authorization).
     */
    void acceptApplication(UUID projectId, UUID applicationId, UUID employerId);

    /**
     * Rejects a specific application for a project.
     *
     * @param projectId     The UUID of the project.
     * @param applicationId The UUID of the application to reject.
     * @param employerId    The UUID of the employer performing the action (for authorization).
     */
    void rejectApplication(UUID projectId, UUID applicationId, UUID employerId);

    /**
     * Deactivates a project and rejects all its pending applications.
     *
     * @param projectId  The UUID of the project to deactivate.
     * @param employerId The UUID of the employer performing the action (for authorization).
     */
    void deactivateProject(UUID projectId, UUID employerId);
}
