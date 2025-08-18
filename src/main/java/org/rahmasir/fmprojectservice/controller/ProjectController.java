package org.rahmasir.fmprojectservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rahmasir.fmprojectservice.dto.*;
import org.rahmasir.fmprojectservice.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projects", description = "APIs for managing projects and applications")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Create a new project", description = "Allows an employer to create a new project.")
    public ResponseEntity<ProjectDetailsDto> createProject(@Valid @RequestBody CreateProjectRequest request, @AuthenticationPrincipal Jwt jwt) {
        UUID employerId = UUID.fromString(jwt.getSubject());
        ProjectDetailsDto createdProject = projectService.createProject(request, employerId);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List all projects", description = "Public endpoint to list all available projects.")
    public ResponseEntity<List<ProjectSummaryDto>> listAllProjects() {
        return ResponseEntity.ok(projectService.listAllProjects());
    }

    @GetMapping("/{projectId}")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Get project details", description = "Get the full details of a specific project.")
    public ResponseEntity<ProjectDetailsDto> getProjectDetails(@PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getProjectDetails(projectId));
    }

    @PostMapping("/{projectId}/apply")
    @PreAuthorize("hasRole('FREELANCER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Apply to a project", description = "Allows a freelancer to submit an application for a project.")
    public ResponseEntity<ApplicationDto> applyToProject(@PathVariable UUID projectId, @Valid @RequestBody ApplyToProjectRequest request, @AuthenticationPrincipal Jwt jwt) {
        UUID freelancerId = UUID.fromString(jwt.getSubject());
        ApplicationDto application = projectService.applyToProject(projectId, freelancerId, request);
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/applications")
    @PreAuthorize("hasRole('EMPLOYER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "View project applications", description = "Allows the project owner to view all applications for their project.")
    public ResponseEntity<List<ApplicationDto>> viewApplications(@PathVariable UUID projectId, @AuthenticationPrincipal Jwt jwt) {
        UUID employerId = UUID.fromString(jwt.getSubject());
        return ResponseEntity.ok(projectService.viewApplications(projectId, employerId));
    }

    @PostMapping("/{projectId}/applications/{applicationId}/reject")
    @PreAuthorize("hasRole('EMPLOYER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Reject an application", description = "Allows the project owner to reject a specific application.")
    public ResponseEntity<Void> rejectApplication(@PathVariable UUID projectId, @PathVariable UUID applicationId, @AuthenticationPrincipal Jwt jwt) {
        UUID employerId = UUID.fromString(jwt.getSubject());
        projectService.rejectApplication(projectId, applicationId, employerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{projectId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Deactivate a project", description = "Allows the project owner to deactivate a project, which rejects all pending applications.")
    public ResponseEntity<Void> deactivateProject(@PathVariable UUID projectId, @AuthenticationPrincipal Jwt jwt) {
        UUID employerId = UUID.fromString(jwt.getSubject());
        projectService.deactivateProject(projectId, employerId);
        return ResponseEntity.noContent().build();
    }
}
