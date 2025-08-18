package org.rahmasir.fmprojectservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.rahmasir.fmprojectservice.dto.*;
import org.rahmasir.fmprojectservice.entity.Application;
import org.rahmasir.fmprojectservice.entity.Project;
import org.rahmasir.fmprojectservice.mapper.ProjectMapper;
import org.rahmasir.fmprojectservice.repository.ApplicationRepository;
import org.rahmasir.fmprojectservice.repository.ProjectRepository;
import org.rahmasir.fmprojectservice.service.ProjectService;
import org.rahmasir.fmprojectservice.service.SkillService;
import org.rahmasir.fmsharedlib.enums.ApplicationStatus;
import org.rahmasir.fmsharedlib.enums.ProjectStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ApplicationRepository applicationRepository;
    private final SkillService skillService;
    private final ProjectMapper projectMapper;

    @Override
    @Transactional
    public ProjectDetailsDto createProject(CreateProjectRequest request, UUID employerId) {
        Project project = new Project(request.name(), request.description(), employerId);
        project.setSkills(skillService.findOrCreateSkills(request.skills()));
        Project savedProject = projectRepository.save(project);
        return projectMapper.toProjectDetailsDto(savedProject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectSummaryDto> listAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toProjectSummaryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDetailsDto getProjectDetails(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found")); // Replace with custom exception
        return projectMapper.toProjectDetailsDto(project);
    }

    @Override
    @Transactional
    public ApplicationDto applyToProject(UUID projectId, UUID freelancerId, ApplyToProjectRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        // Add logic to check if project is ACTIVE, etc.
        Application application = new Application(project, freelancerId, request.message());
        Application savedApplication = applicationRepository.save(application);
        // Here you would publish a notification event to RabbitMQ
        return projectMapper.toApplicationDto(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationDto> viewApplications(UUID projectId, UUID employerId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        if (!project.getEmployerId().equals(employerId)) {
            throw new RuntimeException("Forbidden"); // Replace with custom exception
        }
        return project.getApplications().stream()
                .map(projectMapper::toApplicationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void acceptApplication(UUID projectId, UUID applicationId, UUID employerId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        if (!project.getEmployerId().equals(employerId)) {
            throw new RuntimeException("Forbidden");
        }
        // Add logic to accept one application and reject others
        // Publish notification events for all applicants
    }

    @Override
    @Transactional
    public void rejectApplication(UUID projectId, UUID applicationId, UUID employerId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        if (!project.getEmployerId().equals(employerId)) {
            throw new RuntimeException("Forbidden"); // Use custom exception
        }

        Application application = project.getApplications().stream()
                .filter(app -> app.getId().equals(applicationId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(ApplicationStatus.REJECTED);
        applicationRepository.save(application);
        // Here you would publish a notification event to the freelancer
    }

    @Override
    @Transactional
    public void deactivateProject(UUID projectId, UUID employerId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        if (!project.getEmployerId().equals(employerId)) {
            throw new RuntimeException("Forbidden"); // Use custom exception
        }

        project.setStatus(ProjectStatus.INACTIVE);

        project.getApplications().forEach(application -> {
            if (application.getStatus() == ApplicationStatus.PENDING) {
                application.setStatus(ApplicationStatus.REJECTED);
                // Here you would publish a notification event for each rejected applicant
            }
        });

        projectRepository.save(project);
    }
}
