package org.rahmasir.fmprojectservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.rahmasir.fmsharedlib.enums.ProjectStatus;

@Converter(autoApply = true)
public class ProjectStatusConverter implements AttributeConverter<ProjectStatus, String> {

    @Override
    public String convertToDatabaseColumn(ProjectStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Override
    public ProjectStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return ProjectStatus.valueOf(code);
    }
}
