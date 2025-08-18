package org.rahmasir.fmprojectservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rahmasir.fmprojectservice.dto.ApplicationDto;
import org.rahmasir.fmprojectservice.dto.ProjectDetailsDto;
import org.rahmasir.fmprojectservice.dto.ProjectSummaryDto;
import org.rahmasir.fmprojectservice.dto.SkillDto;
import org.rahmasir.fmprojectservice.entity.Application;
import org.rahmasir.fmprojectservice.entity.Project;
import org.rahmasir.fmprojectservice.entity.Skill;

/**
 * Mapper for converting between project-related entities and DTOs.
 * MapStruct will generate the implementation of this interface at compile time.
 */
@Mapper(componentModel = "spring") // creating spring bean for dependency injection
public interface ProjectMapper {

    /**
     * Converts a Skill entity to a SkillDto.
     *
     * @param skill The Skill entity.
     * @return The corresponding SkillDto.
     */
    SkillDto toSkillDto(Skill skill);

    /**
     * Converts a Project entity to a ProjectSummaryDto for list views.
     *
     * @param project The Project entity.
     * @return The corresponding ProjectSummaryDto.
     */
    ProjectSummaryDto toProjectSummaryDto(Project project);

    /**
     * Converts a Project entity to a ProjectDetailsDto for detailed views.
     *
     * @param project The Project entity.
     * @return The corresponding ProjectDetailsDto.
     */
    ProjectDetailsDto toProjectDetailsDto(Project project);

    /**
     * Converts an Application entity to an ApplicationDto.
     * It explicitly maps the project's ID from the nested Project entity.
     *
     * @param application The Application entity.
     * @return The corresponding ApplicationDto.
     */
    @Mapping(source = "project.id", target = "projectId")
    ApplicationDto toApplicationDto(Application application);
}
