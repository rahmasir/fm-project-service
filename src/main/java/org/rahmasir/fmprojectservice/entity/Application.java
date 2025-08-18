package org.rahmasir.fmprojectservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.rahmasir.fmprojectservice.converter.ApplicationStatusConverter;
import org.rahmasir.fmsharedlib.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "application")
@Getter
@Setter
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private UUID freelancerId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Convert(converter = ApplicationStatusConverter.class)
    @Column(nullable = false)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Application(Project project, UUID freelancerId, String message) {
        this.project = project;
        this.freelancerId = freelancerId;
        this.message = message;
    }
}
