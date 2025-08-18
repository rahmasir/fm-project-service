package org.rahmasir.fmprojectservice.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.rahmasir.fmsharedlib.enums.ApplicationStatus;

@Converter(autoApply = true)
public class ApplicationStatusConverter implements AttributeConverter<ApplicationStatus, String> {

    @Override
    public String convertToDatabaseColumn(ApplicationStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }

    @Override
    public ApplicationStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return ApplicationStatus.valueOf(code);
    }
}
